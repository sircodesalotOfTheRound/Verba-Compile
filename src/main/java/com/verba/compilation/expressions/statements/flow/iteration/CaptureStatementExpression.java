package main.java.verba.language.expressions.statements.flow.iteration;

import com.javalinq.implementations.QList;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-4-28.
 */
public class CaptureStatementExpression extends VerbaExpression {
    private final QList<FullyQualifiedNameExpression> variables;

    public CaptureStatementExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.variables = this.consumeVariables(lexer);
    }

    private QList<FullyQualifiedNameExpression> consumeVariables(Lexer lexer) {
        QList<FullyQualifiedNameExpression> variableList = new QList<>();

        lexer.readCurrentAndAdvance(EnclosureToken.class, "[");

        do {
            variableList.add(FullyQualifiedNameExpression.read(this, lexer));
        } while (lexer.notEOF() && lexer.currentIs(OperatorToken.class, ","));

        lexer.readCurrentAndAdvance(EnclosureToken.class, "]");

        return variableList;
    }

    public static CaptureStatementExpression read(VerbaExpression parent, Lexer lexer) {
        return new CaptureStatementExpression(parent, lexer);
    }
}
