package main.java.verba.language.expressions.categories;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRuleSet;
import main.java.verba.language.expressions.backtracking.rules.AspectDeclarationBacktrackRule;
import main.java.verba.language.expressions.backtracking.rules.HashtagDeclarationBacktrackRule;
import main.java.verba.language.lexing.Lexer;

/**
 * Created by sircodesalot on 14-4-15.
 */
public interface MetaTagExpression {
    public static BacktrackRuleSet<MetaTagExpression> metatags
        = new BacktrackRuleSet<MetaTagExpression>()
        .addRule(new HashtagDeclarationBacktrackRule())
        .addRule(new AspectDeclarationBacktrackRule());

    public static MetaTagExpression read(VerbaExpression expression, Lexer lexer) {
        return metatags.resolve(expression, lexer);
    }
}
