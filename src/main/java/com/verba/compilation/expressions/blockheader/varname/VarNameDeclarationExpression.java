package main.java.verba.language.expressions.blockheader.varname;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.MarkupRvalueExpression;
import main.java.verba.language.expressions.categories.NamedDataDeclarationExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.expressions.containers.tuple.TupleItemExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-17.
 */
public class VarNameDeclarationExpression extends VerbaExpression
    implements RValueExpression, TupleItemExpression, MarkupRvalueExpression,
    NamedDataDeclarationExpression

{
    private final FullyQualifiedNameExpression identifier;
    private TypeDeclarationExpression type;

    public VarNameDeclarationExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.identifier = FullyQualifiedNameExpression.read(this, lexer);

        if (lexer.currentIs(OperatorToken.class, ":")) {
            lexer.readCurrentAndAdvance(OperatorToken.class, ":");
            this.type = TypeDeclarationExpression.read(this, lexer);
        }
    }

    public static VarNameDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
        return new VarNameDeclarationExpression(parent, lexer);
    }

    public String representation() {
        if (type != null) return String.format("%s : %s", identifier.representation(), type.representation());
        else return this.identifier.representation();
    }

    @Override
    public String name() {
        return this.identifier.representation();
    }

    @Override
    public boolean hasTypeConstraint() {
        return (this.type != null);
    }

    @Override
    public TypeDeclarationExpression typeDeclaration() {
        return this.type;
    }

    public FullyQualifiedNameExpression identifier() {
        return this.identifier;
    }
}