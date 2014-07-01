package main.java.verba.language.expressions.rvalue.newexpression;

import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.identifiers.IdentifierToken;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-2-24.
 */
public class NewExpression extends VerbaExpression implements RValueExpression {
    private TypeDeclarationExpression expression;

    public NewExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(KeywordToken.class, "new");
        this.expression = this.parseExpression(lexer);

        if (lexer.currentIs(EnclosureToken.class, "(")) {
            lexer.readCurrentAndAdvance(EnclosureToken.class, "(");
            lexer.readCurrentAndAdvance(EnclosureToken.class, ")");
        }
    }

    private TypeDeclarationExpression parseExpression(Lexer lexer) {
        if (lexer.currentIs(IdentifierToken.class)) return TypeDeclarationExpression.read(this, lexer);
        else throw new ParseException("Expected type name to follow new");
    }

    public static NewExpression read(VerbaExpression parent, Lexer lexer) {
        return new NewExpression(parent, lexer);
    }

    public TypeDeclarationExpression expression() {
        return this.expression;
    }
}
