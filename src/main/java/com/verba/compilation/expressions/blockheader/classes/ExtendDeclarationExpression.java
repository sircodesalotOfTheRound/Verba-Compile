package main.java.verba.language.expressions.blockheader.classes;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.block.BlockDeclarationExpression;
import main.java.verba.language.expressions.blockheader.NamedBlockExpression;
import main.java.verba.language.expressions.blockheader.generic.GenericTypeListExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-2-17.
 */
public class ExtendDeclarationExpression extends VerbaExpression implements NamedBlockExpression {
    private final FullyQualifiedNameExpression identifier;
    private final BlockDeclarationExpression block;
    private final GenericTypeListExpression genericTypeList;

    public ExtendDeclarationExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(KeywordToken.class, "extend");
        this.identifier = FullyQualifiedNameExpression.read(this, lexer);
        this.genericTypeList = GenericTypeListExpression.read(this, lexer);

        this.block = BlockDeclarationExpression.read(this, lexer);
    }

    public static ExtendDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
        return new ExtendDeclarationExpression(parent, lexer);
    }

    private String getTypeListString() {
        if (this.genericTypeList == null) return "";
        else return this.genericTypeList.toString();
    }

    public FullyQualifiedNameExpression identifer() {
        return this.identifier;
    }

    public BlockDeclarationExpression block() {
        return this.block;
    }

    public GenericTypeListExpression genericParameters() {
        return this.genericTypeList;
    }


    @Override
    public String name() {
        return this.identifier.members().first().memberName();
    }
}
