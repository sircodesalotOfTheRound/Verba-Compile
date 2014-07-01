package main.java.verba.language.expressions.statements.flow.iteration;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.block.BlockDeclarationExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-2-26.
 */
public class WhileStatementExpression extends VerbaExpression {
    private RValueExpression testCondition;
    private BlockDeclarationExpression block;

    public WhileStatementExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        lexer.readCurrentAndAdvance(KeywordToken.class, "while");
        lexer.readCurrentAndAdvance(EnclosureToken.class, "(");
        this.testCondition = RValueExpression.read(this, lexer);
        lexer.readCurrentAndAdvance(EnclosureToken.class, ")");

        this.block = BlockDeclarationExpression.read(this, lexer);
    }

    public static WhileStatementExpression read(VerbaExpression parent, Lexer lexer) {
        return new WhileStatementExpression(parent, lexer);
    }

    public RValueExpression testCondition() {
        return this.testCondition;
    }

    public BlockDeclarationExpression block() {
        return this.block;
    }
}
