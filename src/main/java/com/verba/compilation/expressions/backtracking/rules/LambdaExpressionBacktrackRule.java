package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.rvalue.lambda.LambdaExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;

/**
 * Created by sircodesalot on 14-2-28.
 */
public class LambdaExpressionBacktrackRule extends BacktrackRule {

    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        return verifyThenRollback(lexer, lex -> {
            LambdaExpression.read(parent, lexer);
        });
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        return LambdaExpression.read(parent, lexer);
    }
}
