package com.verba.language.test.lexing.tokens.operators.mathop;

import com.verba.language.exceptions.ParseException;
import com.verba.language.test.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-27.
 */
public abstract class MathOpToken extends OperatorToken {
    private String representation;

    public MathOpToken(String representation) {
        super('m');

        this.representation = representation;
    }

    public abstract int getPriorityLevel();

    @Override
    public String toString() {
        return this.representation;
    }

    public static boolean isMathOpToken(Character text) {
        return (text == '+' || text == '-' || text == '*' || text == '/' || text == '%');
    }

    public static MathOpToken cast(Character text) {
        if (text == '+') {
            return new AddOpToken();
        } else if (text == '-') {
            return new SubtractOpToken();
        } else if (text == '*') {
            return new MultiplyOpToken();
        } else if (text == '/') {
            return new DivideOpToken();
        } else if (text == '%') {
            return new ModuloOpToken();
        } else throw new ParseException("Expected math operation");
    }
}
