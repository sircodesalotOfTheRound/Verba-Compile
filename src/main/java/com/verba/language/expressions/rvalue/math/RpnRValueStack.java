package com.verba.language.expressions.rvalue.math;

import com.verba.language.expressions.rvalue.simple.MathOpExpression;

import java.util.Stack;

/**
 * Created by sircodesalot on 14-2-27.
 */

@Deprecated
public class RpnRValueStack {
  private Stack<MathOpExpression> stack = new Stack<MathOpExpression>();

  public void push(MathOpExpression item) {
    this.stack.push(item);
  }

  public MathOpExpression pop() {
    return this.stack.pop();
  }

  public MathOpExpression peek() {
    return this.stack.peek();
  }

  public boolean hasItems() {
    return !stack.empty();
  }
}
