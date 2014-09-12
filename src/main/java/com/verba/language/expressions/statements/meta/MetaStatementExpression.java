package com.verba.language.expressions.statements.meta;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.tokens.operators.OperatorToken;


/**
 * Created by sircodesalot on 14-2-28.
 */
public class MetaStatementExpression extends VerbaExpression {
  private final VerbaExpression statement;

  public MetaStatementExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    lexer.readCurrentAndAdvance(OperatorToken.class, "@");
    this.statement = VerbaExpression.read(this, lexer);
  }

  public static MetaStatementExpression read(VerbaExpression parent, Lexer lexer) {
    return new MetaStatementExpression(parent, lexer);
  }

  public VerbaExpression statement() {
    return this.statement;
  }

  @Override
  public void accept(AstVisitor visitor) {

  }
}
