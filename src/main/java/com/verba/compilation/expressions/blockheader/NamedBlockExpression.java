package main.java.verba.language.expressions.blockheader;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRuleSet;
import main.java.verba.language.expressions.backtracking.rules.*;
import main.java.verba.language.expressions.block.BlockDeclarationExpression;
import main.java.verba.language.expressions.categories.NamedExpression;
import main.java.verba.language.expressions.categories.SymbolTableExpression;
import main.java.verba.language.lexing.Lexer;

/**
 * Created by sircodesalot on 14-2-27.
 */
public interface NamedBlockExpression extends SymbolTableExpression, NamedExpression {
    public static BacktrackRuleSet<NamedBlockExpression> declarations
        = new BacktrackRuleSet<NamedBlockExpression>()
        .addRule(new NamespaceDeclarationBacktrackRule())
        .addRule(new ClassDeclarationBacktrackRule())
        .addRule(new FunctionDeclarationBacktrackRule())
        .addRule(new InjectedClassDeclarationBacktrackRule())
        .addRule(new TraitDeclarationBacktrackRule())
        .addRule(new MetaDeclarationBacktrackRule())
        .addRule(new TaskDeclarationBacktrackRule())
        .addRule(new ExtendDeclarationBacktrackRule())
        .addRule(new SignatureDeclarationBacktrackRule());

    BlockDeclarationExpression block();

    public static NamedBlockExpression read(VerbaExpression parent, Lexer lexer) {
        return declarations.resolve(parent, lexer);
    }
}
