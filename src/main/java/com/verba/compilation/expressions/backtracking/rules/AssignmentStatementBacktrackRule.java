package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.statements.assignment.AssignmentStatementExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.info.TokenSignature;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.identifiers.IdentifierToken;
import main.java.verba.language.lexing.tokens.operators.assignment.AssignmentToken;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class AssignmentStatementBacktrackRule extends BacktrackRule {
    private static TokenSignature[] tupleAssignmentSequence
        = new TokenSignature[]{
        TokenSignature.make(EnclosureToken.class, "("),
        TokenSignature.make(IdentifierToken.class)
    };

    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        // Assignments should start with an LValue
        if (!restOfLine.startsWith(IdentifierToken.class)
            || !restOfLine.startsWithSequence(tupleAssignmentSequence))

            return false;

        // Should contain assignment operator
        if (!restOfLine.contains(AssignmentToken.class)) return false;

        // If these preliminary tests check out, go ahead and try.
        return true;
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        try {
            lexer.setUndoPoint();
            VerbaExpression result = AssignmentStatementExpression.read(parent, lexer);
            lexer.clearUndoPoint();

            return result;
        } catch (ParseException ex) {
            lexer.rollbackToUndoPoint();
            throw MismatchException.getInstance();
        }
    }
}
