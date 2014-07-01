package main.java.verba.language.expressions.containers.json;

import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.expressions.rvalue.simple.IdentifierExpression;
import main.java.verba.language.expressions.rvalue.simple.QuoteExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.QuoteToken;
import main.java.verba.language.lexing.tokens.identifiers.IdentifierToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-24.
 */
public class JsonExpressionPair extends VerbaExpression {
    private VerbaExpression lhs;
    private RValueExpression rhs;

    private JsonExpressionPair(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.lhs = this.readLhsItem(lexer);
        lexer.readCurrentAndAdvance(OperatorToken.class, ":");
        this.rhs = RValueExpression.read(this, lexer);
    }

    private VerbaExpression readLhsItem(Lexer lexer) {
        if (lexer.currentIs(IdentifierToken.class)) return IdentifierExpression.read(this, lexer);
        else if (lexer.currentIs(QuoteToken.class)) return QuoteExpression.read(this, lexer);

        throw new ParseException("Json expressions must start with an type or quotation");
    }

    public static JsonExpressionPair read(VerbaExpression parent, Lexer lexer) {
        return new JsonExpressionPair(parent, lexer);
    }

    public VerbaExpression lhs() {
        return this.lhs;
    }

    public RValueExpression rhs() {
        return this.rhs;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", this.lhs, this.rhs);
    }
}