package main.java.verba.language.expressions.dependencies;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.expressions.rvalue.simple.QuoteExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.QuoteToken;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-5-20.
 */
public class GrabExpression extends VerbaExpression {
    private final VerbaExpression resourceName;

    public GrabExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(KeywordToken.class, "grab");
        this.resourceName = this.readResourceName(lexer);
    }

    private VerbaExpression readResourceName(Lexer lexer) {
        if (lexer.currentIs(QuoteToken.class)) {
            return QuoteExpression.read(this, lexer);
        }

        return FullyQualifiedNameExpression.read(this, lexer);
    }

    public String resourceNameAsString() {
        if (resourceName instanceof FullyQualifiedNameExpression)
            return ((FullyQualifiedNameExpression) this.resourceName).representation();

        else return ((QuoteExpression) this.resourceName).representation();
    }


    public static GrabExpression read(VerbaExpression parent, Lexer lexer) {
        return new GrabExpression(parent, lexer);
    }
}
