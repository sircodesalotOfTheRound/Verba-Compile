package com.verba.scratchpad;

import com.javalinq.interfaces.QIterable;
import com.verba.language.build.codepage.VerbaCodePage;
import com.verba.language.codegen.generators.FunctionGraph;
import com.verba.language.codegen.rendering.FileStreamImageWriter;
import com.verba.language.codegen.rendering.functions.MemoryStreamFunctionRenderer;
import com.verba.language.codegen.rendering.images.ObjectImage;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;

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

    Iterable<ObjectImage> images = staticSpaceExpression.allSubExpressions()
      .ofType(FunctionDeclarationExpression.class)
      .map(FunctionGraph::new)
      .map(FunctionGraph::createImage);

    try (FileStreamImageWriter writer = new FileStreamImageWriter("/Users/sircodesalot/Desktop/program.vbaj")) {
      writer.save(images);
    }

    System.out.println("saved images");
  }
}
