package com.verba.tools.xml;

import com.verba.tools.display.StringTools;
import com.verba.tools.xml.parsing.XmlTag;
import com.verba.tools.xml.parsing.XmlTagType;
import com.verba.tools.xml.parsing.XmlText;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class XmlElement extends XmlNode {
    private final String name;
    private final String content;
    private final List<XmlNode> nodes;

    public XmlElement(String name, String content) {
        this.name = name;
        this.nodes = new ArrayList<>();
        this.content = content;
    }

    public XmlElement(String name) {
        this.name = name;
        this.nodes = new ArrayList<>();
        this.content = StringTools.emptyString();
    }

    public XmlElement(String name, XmlNode ... nodes) {
        this(name, StringTools.emptyString(), nodes);
    }

    public XmlElement(String name, String content, XmlNode ... nodes) {
        this.name = name;
        this.content = content;
        this.nodes = new ArrayList<XmlNode>();

        Collections.addAll(this.nodes, nodes);
    }

    public boolean hasChildren() { return !this.nodes.isEmpty(); }
    public String name() { return this.name; }
    public Iterable<XmlNode> nodes() { return this.nodes; }


    public String getContentIndented(int indent) {
        if (hasChildren()) {
            String start = StringTools.formatIndented(indent, "<%s>", this.name);
            String end = StringTools.formatIndented(indent, "</%s>", this.name);

            StringBuilder middle = new StringBuilder();

            if (content != null) {
                middle.append(content);
            }

            middle.append("\n");

            for (XmlNode node : this.nodes) {
                middle.append(node.getContentIndented(indent + 4));
                middle.append("\n");
            }

            return String.format("%s%s%s", start, middle, end);

        } else {
            if (!content.isEmpty()) {
                return StringTools.formatIndented(indent, "<%1$s>%2$s</%1$s>", this.name, this.content);

            } else {
                return StringTools.formatIndented(indent, "<%s />", this.name);

            }
        }
    }

    @Override
    public String toString() {
        return getContentIndented(0);
    }

    public static XmlElement parse(XmlLexer lexer) {
        XmlTag openingTag = XmlTag.parse(lexer);

        if (openingTag.type() == XmlTagType.SELF_CLOSED) {
            return new XmlElement(openingTag.name());
        }

        XmlText text = XmlText.parse(lexer);

        XmlElement innerElement = null;
        if (!lexer.isEof() && !(XmlTag.peekNextTag(lexer).name().equals(openingTag.name()))) {
            innerElement = XmlElement.parse(lexer);
        }

        XmlTag closingTag = XmlTag.parse(lexer);

        if (innerElement != null) {
            return new XmlElement(openingTag.name(), text.toString(), innerElement);

        } else {
            return new XmlElement(openingTag.name(), text.toString());
        }
    }

    public static XmlElement parse(String text) {
        return parse(new XmlLexer(text));
    }
}
