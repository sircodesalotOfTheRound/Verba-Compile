package com.verba.tools.xml;

import com.verba.tools.display.StringTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class XmlElement extends XmlNode {
    private final String name;
    private final Object content;
    private final List<XmlNode> nodes;

    public XmlElement(String name, Object content) {
        this.name = name;
        this.nodes = new ArrayList<>();
        this.content = content;
    }

    public XmlElement(String name) {
        this.name = name;
        this.nodes = new ArrayList<>();
        this.content = null;
    }

    public XmlElement(String name, XmlNode ... nodes) {
        this.name = name;
        this.content = null;
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
            if (content != null) {
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

}
