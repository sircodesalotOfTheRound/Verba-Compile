package com.verba.run;

import com.verba.language.build.Build;
import com.verba.language.codegen.core.VlitFileGenerator;
import com.verba.language.codegen.functions.VlitFunctionGenerator;
import com.verba.language.codegen.opcodes.datasegments.DataSegment;

/**
 * Created by sircodesalot on 14-7-8.
 */
public class Program {
    public static void main(String[] args) {
        if (args.length < 1) printUsage();

        String path = String.format("%s/%s", System.getProperty("user.dir"), args[0]);

        build(path);
    }

    private static void build(String path) {
        Build build = new Build();
        build.addBuildItemByPath(path);

        VlitFileGenerator fileGenerator = build.build();
        String outputPath = path.replace(".v", ".vbtm");

        fileGenerator.save(outputPath);
    }

    private static void printUsage() {
        System.out.println("Useage: vblz <source path>");

        System.exit(0);
    }
}
