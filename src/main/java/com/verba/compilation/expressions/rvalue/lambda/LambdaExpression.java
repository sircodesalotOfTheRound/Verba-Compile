package main.java.verba.language.expressions.rvalue.lambda;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.LambdaToken;

/**
 * Created by sircodesalot on 14-2-28.
 */
public class LambdaExpression extends VerbaExpression implements RValueExpression {
    private TypeDeclarationExpression lvalue;
    private RValueExpression rvalue;

    public LambdaExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.lvalue = TypeDeclarationExpression.read(this, lexer);
        lexer.readCurrentAndAdvance(LambdaToken.class);

        // Attempt to read RValueExpression
        this.rvalue = RValueExpression.read(this, lexer);
        if (this.rvalue == null) FullyQualifiedNameExpression.read(this, lexer);
    }

    public static LambdaExpression read(VerbaExpression parent, Lexer lexer) {
        return new LambdaExpression(parent, lexer);
    }

    public TypeDeclarationExpression lvalue() {
        return this.lvalue;
    }

    public RValueExpression rvalue() {
        return this.rvalue;
    }
}
