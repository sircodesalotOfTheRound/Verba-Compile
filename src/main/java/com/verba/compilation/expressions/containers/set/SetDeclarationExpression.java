package main.java.verba.language.expressions.containers.set;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.DataContainerExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-5-21.
 */
public class SetDeclarationExpression extends VerbaExpression
    implements DataContainerExpression, RValueExpression {
    private final QList<VerbaExpression> items = new QList<>();

    private SetDeclarationExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(EnclosureToken.class, "{");

        while (lexer.notEOF()
            && !lexer.currentIs(EnclosureToken.class, "}")
            && !lexer.currentIs(OperatorToken.class, ",")) {

            RValueExpression item = RValueExpression.read(this, lexer);
            this.items.add((VerbaExpression) item);

            if (lexer.currentIs(OperatorToken.class, ",")) lexer.readCurrentAndAdvance(OperatorToken.class, ",");
            else break; // Shennanigans
        }

        lexer.readCurrentAndAdvance(EnclosureToken.class, "}");
    }

    public QIterable<VerbaExpression> items() {
        return this.items;
    }

    public static SetDeclarationExpression read(VerbaExpression expression, Lexer lexer) {
        return new SetDeclarationExpression(expression, lexer);
    }
}
