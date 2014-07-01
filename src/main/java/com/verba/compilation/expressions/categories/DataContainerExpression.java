package main.java.verba.language.expressions.categories;

import com.javalinq.interfaces.QIterable;
import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.containers.array.ArrayDeclarationExpression;
import main.java.verba.language.expressions.containers.json.JsonExpression;
import main.java.verba.language.expressions.containers.tuple.TupleDeclarationExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.EnclosureToken;

/**
 * Represents some sort of non-polymorphic container object (Array, Tuple, Json, Set).
 */
public interface DataContainerExpression {

    public QIterable<VerbaExpression> items();

    public static DataContainerExpression read(VerbaExpression parent, Lexer lexer) {
        if (lexer.currentIs(EnclosureToken.class, "[")) return ArrayDeclarationExpression.read(parent, lexer);
        else if (lexer.currentIs(EnclosureToken.class, "(")) return TupleDeclarationExpression.read(parent, lexer);
        else if (lexer.currentIs(EnclosureToken.class, "{")) return JsonExpression.read(parent, lexer);

        throw new ParseException("Expected container parsing.");
    }
}
