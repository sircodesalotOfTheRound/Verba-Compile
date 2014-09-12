package com.verba.language.expressions.blockheader.varname;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.MarkupRvalueExpression;
import com.verba.language.expressions.categories.NamedDataDeclarationExpression;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.expressions.categories.TypeDeclarationExpression;
import com.verba.language.expressions.containers.tuple.TupleItemExpression;
import com.verba.language.expressions.members.FullyQualifiedNameExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-17.
 */
public class NamedObjectDeclarationExpression extends VerbaExpression
  implements RValueExpression, TupleItemExpression, MarkupRvalueExpression,
  NamedDataDeclarationExpression

{
  private final FullyQualifiedNameExpression identifier;
  private TypeDeclarationExpression type;

  public NamedObjectDeclarationExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    this.identifier = FullyQualifiedNameExpression.read(this, lexer);

    if (lexer.currentIs(OperatorToken.class, ":")) {
      lexer.readCurrentAndAdvance(OperatorToken.class, ":");
      this.type = TypeDeclarationExpression.read(this, lexer);
    }
  }

  public static NamedObjectDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
    return new NamedObjectDeclarationExpression(parent, lexer);
  }

  public String representation() {
    if (type != null) return String.format("%s : %s", identifier.representation(), type.representation());
    else return this.identifier.representation();
  }

  @Override
  public String name() {
    return this.identifier.representation();
  }

  @Override
  public boolean hasTypeConstraint() {
    return (this.type != null);
  }

  @Override
  public TypeDeclarationExpression typeDeclaration() {
    return this.type;
  }

  public FullyQualifiedNameExpression identifier() {
    return this.identifier;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }
}
