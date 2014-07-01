package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.statements.meta.MetaStatementExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-23.
 */
public class MetaStatementBacktrackRule extends BacktrackRule {
    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        return restOfLine.startsWith(OperatorToken.class, "@");
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        return tryWithRollback(lexer, () -> MetaStatementExpression.read(parent, lexer));
    }
}
