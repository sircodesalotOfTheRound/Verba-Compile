package com.verba.language.expressions.backtracking.rules;

import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.backtracking.BacktrackRule;
import com.verba.language.expressions.backtracking.MismatchException;
import com.verba.language.expressions.statements.flow.branch.IfStatementExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.info.LexList;
import com.verba.language.test.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-2-26.
 */
public class IfStatementBacktrackRule extends BacktrackRule {
  @Override
  public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
    return restOfLine.startsWith(KeywordToken.class, "if");
  }

  @Override
  public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
    if (restOfLine.startsWith(KeywordToken.class, "if")) return IfStatementExpression.read(parent, lexer);
    throw MismatchException.getInstance();
  }
}
