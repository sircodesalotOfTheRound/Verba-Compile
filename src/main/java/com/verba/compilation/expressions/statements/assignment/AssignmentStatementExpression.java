package main.java.verba.language.expressions.statements.assignment;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.expressions.categories.StatementExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexInfo;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;
import main.java.verba.language.lexing.tokens.operators.assignment.AssignmentToken;
import main.java.verba.language.lexing.tokens.operators.assignment.CompositeAssignmentToken;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class AssignmentStatementExpression extends VerbaExpression implements StatementExpression {
    TypeDeclarationExpression lvalue;
    LexInfo operation;
    RValueExpression rvalue;

    public AssignmentStatementExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.lvalue = TypeDeclarationExpression.read(this, lexer);

        if (lexer.currentIs(OperatorToken.class, "=")
            || lexer.currentIs(CompositeAssignmentToken.class)) {

            this.operation = lexer.readCurrentAndAdvance(AssignmentToken.class);
        }

        this.rvalue = RValueExpression.read(this, lexer);
    }

    public static AssignmentStatementExpression read(VerbaExpression parent, Lexer lexer) {
        return new AssignmentStatementExpression(parent, lexer);
    }

    public TypeDeclarationExpression lvalue() {
        return this.lvalue;
    }

    public LexInfo operation() {
        return this.operation;
    }

    public RValueExpression rvalue() {
        return this.rvalue;
    }

}
