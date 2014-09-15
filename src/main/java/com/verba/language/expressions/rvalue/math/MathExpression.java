package com.verba.language.expressions.rvalue.math;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.tokens.operators.mathop.MathOpToken;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class MathExpression extends VerbaExpression implements RValueExpression {
  // Should probabaly be a tree rather than a list.
  private final QList<BinaryMathExpression> expressions = new QList<>();

  private MathExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    while (lexer.notEOF() && isNextMathToken(lexer)) {
      this.expressions.add(BinaryMathExpression.read(parent, lexer));
    }

  }

  private boolean isNextMathToken(Lexer lexer) {
    boolean isIt = false;

    lexer.setUndoPoint();
    lexer.advance();
    if (lexer.notEOF() && lexer.currentIs(MathOpToken.class)) {
      isIt = true;
    }
    lexer.rollbackToUndoPoint();

    return isIt;
  }

  public static MathExpression read(VerbaExpression parent, Lexer lexer) {
    return new MathExpression(parent, lexer);
  }

  public QIterable<BinaryMathExpression> expressions() {
    return this.expressions;
  }

  @Override
  public void accept(AstVisitor visitor) {

  }
}
