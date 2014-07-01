package main.java.verba.language.expressions.rvalue.pemdas;

import main.java.verba.language.expressions.rvalue.simple.MathOpExpression;

import java.util.Stack;

/**
 * Created by sircodesalot on 14-2-27.
 */
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
