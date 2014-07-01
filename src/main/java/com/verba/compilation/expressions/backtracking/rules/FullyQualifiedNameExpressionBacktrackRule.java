package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokens.identifiers.IdentifierToken;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-4-26.
 */
public class FullyQualifiedNameExpressionBacktrackRule extends BacktrackRule {

    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        return lexer.currentIs(IdentifierToken.class) && !lexer.currentIs(KeywordToken.class);
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        return FullyQualifiedNameExpression.read(parent, lexer);
    }
}
