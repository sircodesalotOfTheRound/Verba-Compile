package com.verba.language.expressions.statements.returns;

import com.verba.language.ast.FunctionElementVisitor;
import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.codegen.generators.FunctionGraph;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-2-22.
 */

public class ReturnStatementExpression extends VerbaExpression {
  private RValueExpression value;

  public ReturnStatementExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    int currentLine = lexer.current().line();
    lexer.readCurrentAndAdvance(KeywordToken.class, "return");

    if (lexer.current().line() == currentLine) {
      this.value = RValueExpression.read(this, lexer);
    }

    this.closeLexingRegion();
  }

  public static ReturnStatementExpression read(VerbaExpression expression, Lexer lexer) {
    return new ReturnStatementExpression(expression, lexer);
  }

  public RValueExpression value() {
    return this.value;
  }

  public boolean hasValue() {
    return this.value != null;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

}
