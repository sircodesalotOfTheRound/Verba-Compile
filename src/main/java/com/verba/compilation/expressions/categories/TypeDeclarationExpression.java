package main.java.verba.language.expressions.categories;

import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.containers.tuple.TupleDeclarationExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokenization.Token;

/**
 * Created by sircodesalot on 14-2-25.
 */
public interface TypeDeclarationExpression extends Token {


    public static TypeDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
        if (FullyQualifiedNameExpression.IsFullyQualifiedName(lexer)) {
            return FullyQualifiedNameExpression.read(parent, lexer);
        } else if (TupleDeclarationExpression.isTupleTypeDeclaration(lexer)) {
            return TupleDeclarationExpression.read(parent, lexer);
        }

        throw new ParseException("Expected Type Declaration");
    }

    public abstract String representation();


}
