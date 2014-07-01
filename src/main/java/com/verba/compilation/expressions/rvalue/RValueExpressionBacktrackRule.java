package main.java.verba.language.expressions.rvalue;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;

/**
 * Created by sircodesalot on 14-4-26.
 */
public class RValueExpressionBacktrackRule extends BacktrackRule {

    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        return super.verifyThenRollback(lexer, lex -> {
            RValueExpression.read(parent, lexer);
        });
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        return (VerbaExpression) RValueExpression.read(parent, lexer);
    }
}
