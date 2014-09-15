package com.verba.language.expressions.rvalue.math;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.backtracking.BacktrackRuleSet;
import com.verba.language.expressions.backtracking.rules.*;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.info.LexInfo;
import com.verba.language.test.lexing.tokens.operators.mathop.MathOpToken;

/**
 * Created by sircodesalot on 14/9/15.
 */
public class BinaryMathExpression extends VerbaExpression {
  private final RValueExpression lhs;
  private final LexInfo operation;
  private final RValueExpression rhs;

  // We have to use a specialized rule set because MathExpressions
  // are RValueExpressions which means RValueExpression.read
  // would cause a StackOverflow.
  private static BacktrackRuleSet<RValueExpression> mathRuleSet
    = new BacktrackRuleSet<RValueExpression>()
    .addRule(new LiteralExpressionRule())
    .addRule(new LambdaExpressionBacktrackRule())
    .addRule(new NewExpressionBacktrackRule())
    .addRule(new CastedRValueExpressionBacktrackRule())
    .addRule(new RValueContainerExpressionBacktrackRule());

  public BinaryMathExpression(VerbaExpression parent, Lexer lexer) {
    super (parent, lexer);

    lhs = mathRuleSet.resolve(parent, lexer);
    operation = lexer.readCurrentAndAdvance(MathOpToken.class);
    rhs = mathRuleSet.resolve(parent, lexer);
  }

  public static BinaryMathExpression read(VerbaExpression parent, Lexer lexer) {
    return new BinaryMathExpression(parent, lexer);
  }

  public RValueExpression lhs() { return this.lhs; }
  public LexInfo operation() { return this.operation; }
  public RValueExpression rhs() { return this.rhs; }

  // TODO: Make AstVisitor only valid for specific items.
  @Override
  public void accept(AstVisitor visitor) {

  }
}
