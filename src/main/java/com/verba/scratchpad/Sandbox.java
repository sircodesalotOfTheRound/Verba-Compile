package com.verba.scratchpad;

import com.verba.language.codegen.generators.FunctionImageSegmentGenerator;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.parsing.VerbaMemoizingLexer;
import com.verba.language.parsing.codestream.FileBasedCodeStream;

/**
 * Created by sircodesalot on 14-2-16.
 */
public class Sandbox {
  public static void main(String[] args) throws Exception {
    FileBasedCodeStream codeStream = new FileBasedCodeStream("SimpleSource.v");
    VerbaMemoizingLexer lexer = new VerbaMemoizingLexer("SimpleSource.v", codeStream);


    FunctionDeclarationExpression expression = (FunctionDeclarationExpression)VerbaExpression.read(null, lexer);

    FunctionImageSegmentGenerator generator = new FunctionImageSegmentGenerator(expression);
  }
}
