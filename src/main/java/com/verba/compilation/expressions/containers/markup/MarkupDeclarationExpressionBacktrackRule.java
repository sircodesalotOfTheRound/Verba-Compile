package main.java.verba.language.expressions.containers.markup;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-5-22.
 */
public class MarkupDeclarationExpressionBacktrackRule extends BacktrackRule {
    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        return lexer.currentIs(OperatorToken.class, "<");
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        return MarkupDeclarationExpression.read(parent, lexer);
    }
}
