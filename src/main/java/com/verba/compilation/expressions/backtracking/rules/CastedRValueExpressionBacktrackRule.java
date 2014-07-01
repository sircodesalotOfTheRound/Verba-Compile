package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.rvalue.cast.CastedRValueExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokens.EnclosureToken;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class CastedRValueExpressionBacktrackRule extends BacktrackRule {
    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        if (!restOfLine.startsWith(EnclosureToken.class, "(")) return false;

        try {
            lexer.setUndoPoint();
            CastedRValueExpression.read(parent, lexer);
            lexer.rollbackToUndoPoint();
            return true;

        } catch (ParseException ex) {
            lexer.rollbackToUndoPoint();
            return false;
        }
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        return CastedRValueExpression.read(parent, lexer);
    }
}
