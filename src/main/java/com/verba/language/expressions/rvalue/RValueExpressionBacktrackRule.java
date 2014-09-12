package com.verba.language.expressions.rvalue;

import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.backtracking.BacktrackRule;
import com.verba.language.expressions.backtracking.MismatchException;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.info.LexList;

/**
 * Created by sircodesalot on 14-4-26.
 */
public class RValueExpressionBacktrackRule extends BacktrackRule {

  @Override
  public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
    return super.verifyThenRollback(lexer, lex -> {
      RValueExpression.read(parent, lexer);
    });
  }

  @Override
  public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
    return (VerbaExpression) RValueExpression.read(parent, lexer);
  }
}
