package main.java.verba.language.expressions.blockheader.functions;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.blockheader.generic.GenericTypeListExpression;
import main.java.verba.language.expressions.categories.NamedExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.expressions.containers.tuple.TupleDeclarationExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-17.
 */
public class SignatureDeclarationExpression extends VerbaExpression implements NamedExpression {
    private final FullyQualifiedNameExpression identifier;
    private final TypeDeclarationExpression returnType;
    private final TupleDeclarationExpression parameters;
    private final GenericTypeListExpression genericTypeList;

    public SignatureDeclarationExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(KeywordToken.class, "signature");
        this.identifier = FullyQualifiedNameExpression.read(this, lexer);

        this.genericTypeList = GenericTypeListExpression.read(this, lexer);
        this.parameters = TupleDeclarationExpression.read(this, lexer);

        lexer.readCurrentAndAdvance(OperatorToken.class, ":");
        this.returnType = TypeDeclarationExpression.read(this, lexer);
    }

    public static SignatureDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
        return new SignatureDeclarationExpression(parent, lexer);
    }

    public boolean hasParameters() {
        return this.parameters.hasItems();
    }

    public boolean hasGenericParameters() {
        return this.genericTypeList.hasItems();
    }

    public FullyQualifiedNameExpression identifier() {
        return this.identifier;
    }

    public TypeDeclarationExpression returnType() {
        return this.returnType;
    }

    public TupleDeclarationExpression parameters() {
        return this.parameters;
    }

    public GenericTypeListExpression genericParamters() {
        return this.genericTypeList;
    }

    @Override
    public String name() {
        return this.identifier.members().first().memberName();
    }
}
