package com.verba.language.expressions.statements.assignment;

import com.verba.language.ast.FunctionElementVisitor;
import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.codegen.generators.FunctionGraph;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.expressions.categories.TypeDeclarationExpression;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.info.LexInfo;
import com.verba.language.parsing.tokens.operators.OperatorToken;
import com.verba.language.parsing.tokens.operators.assignment.AssignmentToken;
import com.verba.language.parsing.tokens.operators.assignment.CompositeAssignmentToken;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class AssignmentStatementExpression extends VerbaExpression {
  TypeDeclarationExpression lvalue;
  LexInfo operation;
  RValueExpression rvalue;

  public AssignmentStatementExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    this.lvalue = TypeDeclarationExpression.read(this, lexer);

    if (lexer.currentIs(OperatorToken.class, "=")
      || lexer.currentIs(CompositeAssignmentToken.class)) {

      this.operation = lexer.readCurrentAndAdvance(AssignmentToken.class);
    }

    this.rvalue = RValueExpression.read(this, lexer);
    this.closeLexingRegion();
  }

  public static AssignmentStatementExpression read(VerbaExpression parent, Lexer lexer) {
    return new AssignmentStatementExpression(parent, lexer);
  }

  public TypeDeclarationExpression lvalue() {
    return this.lvalue;
  }

  public LexInfo operation() {
    return this.operation;
  }

  public RValueExpression rvalue() {
    return this.rvalue;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }
}
