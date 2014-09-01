package com.verba.scratchpad;

import com.verba.tools.xml.XmlElement;
import com.verba.tools.xml.XmlLexer;
import com.verba.tools.xml.parsing.XmlTag;

/**
 * Created by sircodesalot on 14-2-16.
 */
public class Sandbox {
    public static void main(String[] args) throws Exception {
        XmlElement element = XmlElement.parse("<first><second><third>Text<fourth/></third></second></first>");
        System.out.println(element.getContentIndented(1));
    }
}
