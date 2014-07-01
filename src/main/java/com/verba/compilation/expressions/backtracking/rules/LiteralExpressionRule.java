package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.rvalue.simple.NumericExpression;
import main.java.verba.language.expressions.rvalue.simple.QuoteExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexInfo;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokens.NumericToken;
import main.java.verba.language.lexing.tokens.QuoteToken;

/**
 * Created by sircodesalot on 14-2-22.
 */
public class LiteralExpressionRule extends BacktrackRule {
    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        return (restOfLine.startsWith(NumericToken.class) || restOfLine.startsWith(QuoteToken.class));
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        LexInfo nextToken = restOfLine.first();
        if (nextToken.is(NumericToken.class)) return NumericExpression.read(parent, lexer);
        else if (nextToken.is(QuoteToken.class)) return QuoteExpression.read(parent, lexer);

        throw MismatchException.getInstance();
    }
}
