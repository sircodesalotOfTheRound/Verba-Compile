package com.verba.language.expressions.statements.declaration;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.build.resolution.SymbolResolver;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.varname.NamedValueExpression;
import com.verba.language.expressions.categories.*;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.tokens.identifiers.KeywordToken;
import com.verba.language.parsing.tokens.operators.assignment.AssignmentToken;
import com.verba.language.symbols.table.tables.ScopedSymbolTable;

/**
 * Created by sircodesalot on 14-2-19.
 */
public class MutableDeclarationStatement extends VerbaExpression
  implements NamedAndTypedExpression, AssignmentExpression, SymbolTableExpression, ResolvableTypeExpression {

  private NamedValueExpression identifier;
  private RValueExpression rvalue;

  private MutableDeclarationStatement(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    this.readExpression(lexer);
    this.closeLexingRegion();
  }

  private void readExpression(Lexer lexer) {
    lexer.readCurrentAndAdvance(KeywordToken.class, "mut");
    this.identifier = NamedValueExpression.read(this, lexer);

    if (lexer.currentIs(AssignmentToken.class)) {
      lexer.readCurrentAndAdvance(AssignmentToken.class, "=");
      this.rvalue = RValueExpression.read(this, lexer);
    }
  }

  public static MutableDeclarationStatement read(VerbaExpression parent, Lexer lexer) {
    return new MutableDeclarationStatement(parent, lexer);
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

  public NamedValueExpression identifier() {
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
