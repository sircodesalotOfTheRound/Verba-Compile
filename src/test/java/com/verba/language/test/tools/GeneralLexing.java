package com.verba.language.test.tools;

import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.VerbaMemoizingLexer;
import com.verba.language.test.lexing.codestream.CodeStream;
import com.verba.language.test.lexing.codestream.FileBasedCodeStream;
import com.verba.language.test.lexing.codestream.StringBasedCodeStream;

import java.io.InputStream;

/**
 * Created by sircodesalot on 14-3-21.
 */
public class GeneralLexing {
    public static Lexer generateLexerFromString(String code) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return generateLexerFromString(stackTrace[2].getMethodName(), code);
    }

    public static Lexer generateLexerFromString(String filename, String code) {
        return GeneralLexing.generateLexerFromString(filename, code, false);
    }

    public static Lexer generateLexerFromString(String filename, String code, boolean includeWhitespace) {
        CodeStream stream = new StringBasedCodeStream(filename, code);
        return new VerbaMemoizingLexer(filename, stream, includeWhitespace, false);
    }


    public static Lexer generateFromResourceFile(String filename) throws Exception {
        InputStream resourceStream = GeneralLexing.class.getResourceAsStream(filename);
        CodeStream stream = new FileBasedCodeStream(filename, resourceStream);

        return new VerbaMemoizingLexer(filename, stream, false, false);
    }
}
