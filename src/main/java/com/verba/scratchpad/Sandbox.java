package com.verba.scratchpad;

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
