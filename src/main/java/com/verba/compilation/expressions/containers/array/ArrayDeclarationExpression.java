package main.java.verba.language.expressions.containers.array;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.DataContainerExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexInfo;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.NumericToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-24.
 */
public class ArrayDeclarationExpression extends VerbaExpression implements RValueExpression,
    DataContainerExpression {

    QList<VerbaExpression> items = new QList<>();

    public ArrayDeclarationExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(EnclosureToken.class, "[");
        while (lexer.notEOF() && !lexer.currentIs(EnclosureToken.class, "]")) {
            items.add(VerbaExpression.read(parent, lexer));
            if (lexer.currentIs(OperatorToken.class, ",")) lexer.readCurrentAndAdvance();
        }
        lexer.readCurrentAndAdvance(EnclosureToken.class, "]");
    }

    private LexInfo readContents(Lexer lexer) {
        return lexer.readCurrentAndAdvance(NumericToken.class);
    }

    @Override
    public QIterable<VerbaExpression> items() {
        return this.items;
    }

    public static ArrayDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
        return new ArrayDeclarationExpression(parent, lexer);
    }
}

