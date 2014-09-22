package com.verba.language.expressions.rvalue.simple;

import com.verba.language.ast.FunctionElementVisitor;
import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.codegen.generators.FunctionGraph;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.*;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.info.LexInfo;
import com.verba.language.parsing.tokens.QuoteToken;
import com.verba.virtualmachine.VirtualMachineNativeTypes;

/**
 * Created by sircodesalot on 14-2-19.
 */
public class QuoteExpression extends VerbaExpression
  implements LiteralExpression, NativeTypeExpression, MathOperandExpression,
  FunctionElementExpression, RegisterAllocated {
  private final LexInfo token;

  public QuoteExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);
    this.token = lexer.readCurrentAndAdvance(QuoteToken.class);
    this.closeLexingRegion();
  }

  public static QuoteExpression read(VerbaExpression parent, Lexer lexer) {
    return new QuoteExpression(parent, lexer);
  }

  public String representation() {
    return token.representation();
  }

  public String innerText() {
    String rep = representation();
    return rep.substring(1, rep.length() - 1);
  }

  public LexInfo quotation() {
    return this.token;
  }

  @Override
  public void accept(AstVisitor visitor) {

  }

  @Override
  public TypeDeclarationExpression nativeTypeDeclaration() {
    return VirtualMachineNativeTypes.UTF8;
  }

  @Override
  public void accept(FunctionElementVisitor visitor) {
    visitor.visit(this);
  }
}
