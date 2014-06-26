package com.verba.language.expressions.rvalue.cast;

import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.expressions.categories.TypeDeclarationExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.tokens.EnclosureToken;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class CastedRValueExpression extends VerbaExpression implements RValueExpression {
    private TypeDeclarationExpression toType;
    private RValueExpression rvalue;

    private CastedRValueExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(EnclosureToken.class, "(");
        this.toType = TypeDeclarationExpression.read(this, lexer);
        lexer.readCurrentAndAdvance(EnclosureToken.class, ")");

        this.rvalue = RValueExpression.read(this, lexer);
    }

    public static CastedRValueExpression read(VerbaExpression parent, Lexer lexer) {
        return new CastedRValueExpression(parent, lexer);
    }

    public TypeDeclarationExpression toType() {
        return this.toType;
    }

    public RValueExpression rvalue() {
        return this.rvalue;
    }
}
