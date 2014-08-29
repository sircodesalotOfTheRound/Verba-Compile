package com.verba.vblz.helpers;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class ConsoleOutput {
    public static void println() {
        System.out.println();
    }

    public static void println(String format, Object ... args) {
        String formattedString = String.format(format, args);
        System.out.println(formattedString);
    }

    public static void printlnOk(String format, Object ... args) {
        String formattedString = String.format(format, args);
        println("[OK]\t\t%s", formattedString);
    }
}
