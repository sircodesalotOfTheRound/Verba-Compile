package com.verba.language.expressions.categories;

import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.backtracking.BacktrackRuleSet;
import com.verba.language.expressions.backtracking.rules.*;
import com.verba.language.test.lexing.Lexer;

/**
 * Created by sircodesalot on 14/9/18.
 */
public interface MathOperandExpression {
  public static BacktrackRuleSet<MathOperandExpression> ruleset
    = new BacktrackRuleSet<MathOperandExpression>()
      .addRule(new LiteralExpressionRule());

  public static MathOperandExpression read(VerbaExpression parent, Lexer lexer) {
    return ruleset.resolve(parent, lexer);
  }
}
