package main.java.verba.language.expressions.containers.tuple;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRuleSet;
import main.java.verba.language.expressions.rvalue.RValueExpressionBacktrackRule;
import main.java.verba.language.lexing.Lexer;

/**
 * Created by sircodesalot on 14-4-26.
 */
public interface TupleItemExpression {
    static BacktrackRuleSet<TupleItemExpression> rules = new BacktrackRuleSet<TupleItemExpression>()
        .addRule(new RValueExpressionBacktrackRule());


    public static TupleItemExpression read(VerbaExpression parent, Lexer lexer) {
        return rules.resolve(parent, lexer);
    }
}
