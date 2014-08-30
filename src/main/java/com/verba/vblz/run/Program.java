package com.verba.vblz.run;

import com.verba.tools.display.ConsoleOutput;
import com.verba.vblz.run.argparse.ArgumentParser;

import java.io.Serializable;

/**
 * Created by sircodesalot on 14-7-8.
 */
public class Program implements Serializable {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) printUsage();

        ArgumentParser parser = new ArgumentParser(args);
        parser.runApplication();
    }

    private static void printUsage() {
        System.out.println();
        System.out.println("Useage: vblz <command>");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("    new     : Create a new project schema in this folder.");
        System.out.println("    build   : Build the project located in this folder.");
        ConsoleOutput.printBlankline();

        System.exit(0);
    }
}
