package com.verba.language.expressions.statements.declaration;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.build.resolution.SymbolResolver;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.varname.NamedObjectDeclarationExpression;
import com.verba.language.expressions.categories.*;
import com.verba.language.symbols.table.tables.ScopedSymbolTable;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.tokens.identifiers.KeywordToken;
import com.verba.language.test.lexing.tokens.operators.assignment.AssignmentToken;

/**
 * Created by sircodesalot on 14-2-19.
 */
public class MutaDeclarationStatement extends VerbaExpression
  implements NamedDataDeclarationExpression, AssignmentExpression, SymbolTableExpression, ResolvableTypeExpression {

  private NamedObjectDeclarationExpression identifier;
  private RValueExpression rvalue;

  private MutaDeclarationStatement(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    this.readExpression(lexer);
  }

  private void readExpression(Lexer lexer) {
    lexer.readCurrentAndAdvance(KeywordToken.class, "muta");
    this.identifier = NamedObjectDeclarationExpression.read(this, lexer);

    if (lexer.currentIs(AssignmentToken.class)) {
      lexer.readCurrentAndAdvance(AssignmentToken.class, "=");
      this.rvalue = RValueExpression.read(this, lexer);
    }
  }

  public static MutaDeclarationStatement read(VerbaExpression parent, Lexer lexer) {
    return new MutaDeclarationStatement(parent, lexer);
  }


  @Override
  public boolean hasTypeConstraint() {
    return this.identifier.hasTypeConstraint();
  }

  @Override
  public String name() {
    return this.identifier.name();
  }

  @Override
  public TypeDeclarationExpression typeDeclaration() {
    return this.identifier.typeDeclaration();
  }

  public NamedObjectDeclarationExpression identifier() {
    return this.identifier;
  }

  @Override
  public boolean hasRValue() {
    return this.rvalue != null;
  }

  @Override
  public RValueExpression rvalue() {
    return this.rvalue;
  }

  @Override
  public void accept(AstVisitor visitor) {

  }

  @Override
  public void accept(ScopedSymbolTable symbolTable) {
    symbolTable.visit(this);
  }

  @Override
  public void accept(SymbolResolver resolver) {
    resolver.visit(this);
  }
}
