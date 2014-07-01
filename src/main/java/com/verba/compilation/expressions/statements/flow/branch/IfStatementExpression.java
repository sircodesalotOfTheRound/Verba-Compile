package main.java.verba.language.expressions.statements.flow.branch;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.block.BlockDeclarationExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-2-26.
 */
public class IfStatementExpression extends VerbaExpression {
    private RValueExpression testExpression;
    private BlockDeclarationExpression block;

    public IfStatementExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(KeywordToken.class, "if");
        lexer.readCurrentAndAdvance(EnclosureToken.class, "(");
        this.testExpression = RValueExpression.read(this, lexer);
        lexer.readCurrentAndAdvance(EnclosureToken.class, ")");

        this.block = BlockDeclarationExpression.read(this, lexer);
    }

    public static IfStatementExpression read(VerbaExpression parent, Lexer lexer) {
        return new IfStatementExpression(parent, lexer);
    }

    public RValueExpression testExpression() {
        return this.testExpression;
    }

    public BlockDeclarationExpression block() {
        return this.block;
    }

}
