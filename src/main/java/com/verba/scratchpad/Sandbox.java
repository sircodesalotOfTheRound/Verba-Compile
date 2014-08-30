package com.verba.scratchpad;

import com.verba.language.build.Build;
import com.verba.language.build.BuildItem;
import com.verba.language.codegen.core.VlitFileGenerator;
import com.verba.language.codegen.functions.VlitFunctionGenerator;
import com.verba.language.codegen.opcodes.datasegments.DataSegment;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.symbols.table.tables.GlobalSymbolTable;
import com.verba.language.test.lexing.VerbaMemoizingLexer;
import com.verba.language.test.lexing.codestream.CodeStream;
import com.verba.language.test.lexing.codestream.FileBasedCodeStream;
import com.verba.tools.display.ConsoleOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

/**
 * Created by sircodesalot on 14-2-16.
 */
public class Sandbox {
    public static void main(String[] args) throws Exception {
        CodeStream stream = new FileBasedCodeStream("SimpleSource.v");
        VerbaMemoizingLexer lexer = new VerbaMemoizingLexer("SimpleSource.v", stream);
        VerbaExpression expression = VerbaExpression.read(null, lexer);

        StaticSpaceExpression staticSpaceExpression = new StaticSpaceExpression(expression);

        ObjectOutputStream serializer = new ObjectOutputStream(System.out);
        serializer.writeObject(staticSpaceExpression);


        ConsoleOutput.printBlankline();

    }
}
