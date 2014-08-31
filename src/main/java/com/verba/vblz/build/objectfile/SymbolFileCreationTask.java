package com.verba.vblz.build.objectfile;

import com.verba.language.build.codepage.VerbaCodePage;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.test.lexing.VerbaMemoizingLexer;
import com.verba.language.test.lexing.codestream.CodeStream;
import com.verba.language.test.lexing.codestream.FileBasedCodeStream;
import com.verba.tools.EnvironmentHelpers;
import com.verba.tools.tasks.Task;

import java.io.*;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class SymbolFileCreationTask implements Task {
    private final SourceFilePathInfo sourceFile;

    public SymbolFileCreationTask(SourceFilePathInfo sourceFile) {
        this.sourceFile = sourceFile;
    }

    private VerbaExpression compileFile() {
        CodeStream stream = new FileBasedCodeStream(sourceFile.absolutePath());
        VerbaMemoizingLexer lexer = new VerbaMemoizingLexer(sourceFile.filename(), stream);
        VerbaCodePage codePage = VerbaCodePage.fromFile(null, sourceFile.absolutePath());

        return new StaticSpaceExpression(codePage);
    }

    private void emitToSymbolFile() throws IOException {
        File outputFolder = new File(sourceFile.outputFolder());
        File symbolFile = new File(sourceFile.outputPath());

        // Create the directories
        outputFolder.mkdirs();
        symbolFile.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(symbolFile);
        ObjectOutputStream serializer = new ObjectOutputStream(outputStream);

        VerbaExpression compiledFile = compileFile();
        serializer.writeObject(compiledFile);

        serializer.flush();
        serializer.close();
    }

    @Override
    public void perform() {
        try {
            emitToSymbolFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
