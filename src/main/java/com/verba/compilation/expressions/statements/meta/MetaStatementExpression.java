package main.java.verba.language.expressions.statements.meta;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;


/**
 * Created by sircodesalot on 14-2-28.
 */
public class MetaStatementExpression extends VerbaExpression {
    private final VerbaExpression statement;

    public MetaStatementExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(OperatorToken.class, "@");
        this.statement = VerbaExpression.read(this, lexer);
    }

    public static MetaStatementExpression read(VerbaExpression parent, Lexer lexer) {
        return new MetaStatementExpression(parent, lexer);
    }

    public VerbaExpression statement() {
        return this.statement;
    }
}
