package com.verba.language.expressions.rvalue.simple;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.LiteralExpression;
import com.verba.language.expressions.categories.MathOperandExpression;
import com.verba.language.expressions.categories.NativeTypeExpression;
import com.verba.language.expressions.categories.TypeDeclarationExpression;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.info.LexInfo;
import com.verba.language.parsing.tokens.QuoteToken;
import com.verba.virtualmachine.VirtualMachineNativeTypes;

/**
 * Created by sircodesalot on 14-2-19.
 */
public class QuoteExpression extends VerbaExpression implements LiteralExpression, NativeTypeExpression, MathOperandExpression {
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

  public LexInfo quotation() {
    return this.token;
  }

  @Override
  public void accept(AstVisitor visitor) {

  }

  @Override
  public TypeDeclarationExpression nativeTypeDeclaration() {
    return VirtualMachineNativeTypes.STRING_LITERAL;
  }
}
