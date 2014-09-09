package com.verba.scratchpad;

import com.verba.tools.EnvironmentHelpers;
import com.verba.tools.xml.XmlElement;
import com.verba.tools.xml.XmlLexer;
import com.verba.tools.xml.parsing.XmlTag;
import com.verba.vblz.build.buildscript.BuildScriptDecorator;

/**
 * Created by sircodesalot on 14-2-16.
 */
public class Sandbox {
    public static void main(String[] args) throws Exception {
        BuildScriptDecorator decorator = new BuildScriptDecorator();
        System.out.println(decorator.buildSchemas().first());
    }
}
