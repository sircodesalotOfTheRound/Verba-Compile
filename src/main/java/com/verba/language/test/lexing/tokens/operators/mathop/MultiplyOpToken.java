package com.verba.language.test.lexing.tokens.operators.mathop;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class MultiplyOpToken extends MathOpToken {

  public MultiplyOpToken() {
    super("*");
  }

  @Override
  public int getPriorityLevel() {
    return 2;
  }
}
