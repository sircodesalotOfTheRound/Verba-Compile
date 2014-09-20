package com.verba.language.expressions.blockheader.classes;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.block.BlockDeclarationExpression;
import com.verba.language.expressions.blockheader.NamedBlockExpression;
import com.verba.language.expressions.blockheader.generic.GenericTypeListExpression;
import com.verba.language.expressions.members.FullyQualifiedNameExpression;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.tokens.identifiers.KeywordToken;
import com.verba.language.symbols.table.tables.ScopedSymbolTable;

/**
 * Created by sircodesalot on 14-2-17.
 */
public class InjectedDeclarationExpression extends VerbaExpression implements NamedBlockExpression {
  private final FullyQualifiedNameExpression identifier;
  private final BlockDeclarationExpression block;
  private final GenericTypeListExpression genericParameters;

  public InjectedDeclarationExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    lexer.readCurrentAndAdvance(KeywordToken.class, "injected");
    this.identifier = FullyQualifiedNameExpression.read(this, lexer);
    this.genericParameters = GenericTypeListExpression.read(this, lexer);

    this.block = BlockDeclarationExpression.read(this, lexer);
    this.closeLexingRegion();
  }

  public static InjectedDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
    return new InjectedDeclarationExpression(parent, lexer);
  }

  public FullyQualifiedNameExpression identifier() {
    return this.identifier;
  }

  public BlockDeclarationExpression block() {
    return this.block;
  }

  public GenericTypeListExpression genericParameters() {
    return this.genericParameters;
  }

  @Override
  public String name() {
    return this.identifier.members().first().memberName();
  }

  @Override
  public void accept(AstVisitor visitor) {

  }

  @Override
  public void accept(ScopedSymbolTable symbolTable) {

  }
}
