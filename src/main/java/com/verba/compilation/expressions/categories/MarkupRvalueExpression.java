package main.java.verba.language.expressions.categories;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRuleSet;
import main.java.verba.language.expressions.backtracking.rules.LiteralExpressionRule;
import main.java.verba.language.expressions.backtracking.rules.VarNameExpressionBacktrackRule;
import main.java.verba.language.lexing.Lexer;

/**
 * Created by sircodesalot on 14-5-21.
 */
public interface MarkupRvalueExpression {
    static final BacktrackRuleSet<MarkupRvalueExpression> rules
        = new BacktrackRuleSet<MarkupRvalueExpression>()
        .addRule(new LiteralExpressionRule())
        .addRule(new VarNameExpressionBacktrackRule());

    public static MarkupRvalueExpression read(VerbaExpression parent, Lexer lexer) {
        return rules.resolve(parent, lexer);
    }
}
