package main.java.verba.language.expressions.rvalue.simple;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexInfo;
import main.java.verba.language.lexing.tokens.operators.mathop.MathOpToken;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class MathOpExpression extends VerbaExpression {
    LexInfo operationToken;

    public MathOpExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.operationToken = lexer.readCurrentAndAdvance(MathOpToken.class);
    }

    public LexInfo operator() {
        return this.operationToken;
    }

    public static MathOpExpression read(VerbaExpression parent, Lexer lexer) {
        return new MathOpExpression(parent, lexer);
    }
}
