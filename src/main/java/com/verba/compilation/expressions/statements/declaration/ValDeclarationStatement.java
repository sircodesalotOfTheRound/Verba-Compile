package main.java.verba.language.expressions.statements.declaration;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.blockheader.varname.VarNameDeclarationExpression;
import main.java.verba.language.expressions.categories.AssignmentExpression;
import main.java.verba.language.expressions.categories.NamedDataDeclarationExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-19.
 */
public class ValDeclarationStatement extends VerbaExpression
    implements NamedDataDeclarationExpression, AssignmentExpression {

    private VarNameDeclarationExpression identifier;
    private RValueExpression rvalue;

    private ValDeclarationStatement(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.readExpression(lexer);
    }

    private void readExpression(Lexer lexer) {
        lexer.readCurrentAndAdvance(KeywordToken.class, "val");
        this.identifier = VarNameDeclarationExpression.read(this, lexer);

        lexer.readCurrentAndAdvance(OperatorToken.class, "=");

        this.rvalue = RValueExpression.read(this, lexer);
    }

    public static ValDeclarationStatement read(VerbaExpression parent, Lexer lexer) {
        return new ValDeclarationStatement(parent, lexer);
    }

    @Override
    public String name() {
        return this.identifier.name();
    }

    @Override
    public boolean hasTypeConstraint() {
        return this.identifier.hasTypeConstraint();
    }

    @Override
    public TypeDeclarationExpression typeDeclaration() {
        return this.identifier.typeDeclaration();
    }


    public VarNameDeclarationExpression identifier() {
        return this.identifier;
    }

    @Override
    public boolean hasRValue() {
        return this.rvalue != null;
    }

    @Override
    public RValueExpression rvalue() {
        return this.rvalue;
    }

}
