package com.verba.scratchpad;


import com.verba.language.emit.images.interfaces.ObjectImage;
import com.verba.language.parse.expressions.StaticSpaceExpression;
import com.verba.language.parse.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.parse.expressions.codepage.VerbaCodePage;

/**
 * Created by sircodesalot on 14-2-16.
 */
public class Sandbox {
  public static void main(String[] args) throws Exception {
    //FileBasedCodeStream codeStream = new FileBasedCodeStream("SimpleSource.v");
    //VerbaMemoizingLexer lexer = new VerbaMemoizingLexer("SimpleSource.v", codeStream);
    //FunctionDeclarationExpression expression = (FunctionDeclarationExpression)VerbaExpression.read(null, lexer);

    VerbaCodePage codePage = VerbaCodePage.fromFile(null, "SimpleSource.v");
    StaticSpaceExpression staticSpaceExpression = new StaticSpaceExpression(codePage);

  }
}
