package com.verba.vblz.build.objectfile;

import com.verba.language.build.codepage.VerbaCodePage;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.test.lexing.VerbaMemoizingLexer;
import com.verba.language.test.lexing.codestream.CodeStream;
import com.verba.language.test.lexing.codestream.FileBasedCodeStream;
import com.verba.tools.EnvironmentHelpers;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class ObjectFileCreator {
    private final String inputRelativePath;
    private final String outputRelativePath;
    private StaticSpaceExpression expression;

    public ObjectFileCreator(String relativePath) {
        this.inputRelativePath = relativePath;
        this.outputRelativePath = calculateOutputPath(relativePath);
        this.expression = compileFile(relativePath);
    }

    private StaticSpaceExpression compileFile(String relativePath) {
        CodeStream stream = new FileBasedCodeStream(relativePath);
        VerbaMemoizingLexer lexer = new VerbaMemoizingLexer(relativePath, stream);
        VerbaExpression expression = VerbaCodePage.read(null, lexer);

        return new StaticSpaceExpression(expression);
    }

    private String calculateOutputPath(String relativePath) {
        int lengthWithoutExtension = relativePath.lastIndexOf(".v");
        String pathWithoutExtension = relativePath.substring(lengthWithoutExtension);

        return String.format("%s/%s.o", EnvironmentHelpers.getCurrentFolderName(), pathWithoutExtension);
    }




}
