package com.verba.language.expressions.blockheader;

import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.backtracking.BacktrackRuleSet;
import com.verba.language.expressions.backtracking.rules.*;
import com.verba.language.expressions.block.BlockDeclarationExpression;
import com.verba.language.expressions.categories.NamedExpression;
import com.verba.language.expressions.categories.SymbolTableExpression;
import com.verba.language.test.lexing.Lexer;

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
