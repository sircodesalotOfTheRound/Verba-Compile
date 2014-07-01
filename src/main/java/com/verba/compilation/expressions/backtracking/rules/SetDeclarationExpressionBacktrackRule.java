package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.expressions.containers.set.SetDeclarationExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-5-21.
 */
public class SetDeclarationExpressionBacktrackRule extends BacktrackRule {
    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        lexer.setUndoPoint();

        boolean shouldAttempt = firstItemIsOpenBrace(lexer)
            && secondItemIsRvalueExpression(lexer)
            && thirdItemIsCommaOrBrace(lexer);

        lexer.rollbackToUndoPoint();
        return shouldAttempt;
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        return SetDeclarationExpression.read(parent, lexer);
    }

    private boolean firstItemIsOpenBrace(Lexer lexer) {
        if (lexer.notEOF() && lexer.currentIs(EnclosureToken.class, "{")) {
            lexer.readCurrentAndAdvance(EnclosureToken.class, "{");
            return true;
        }

        return false;
    }

    private boolean secondItemIsRvalueExpression(Lexer lexer) {
        return (lexer.notEOF() && VerbaExpression.read(null, lexer) instanceof RValueExpression);
    }

    private boolean thirdItemIsCommaOrBrace(Lexer lexer) {
        return (lexer.notEOF() && lexer.currentIs(OperatorToken.class, ",") || lexer.currentIs(EnclosureToken.class, "}"));
    }
}
