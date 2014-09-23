package com.verba.language.codegen.function;

import com.verba.language.ast.FunctionElementVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.block.BlockDeclarationExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedValueExpression;
import com.verba.language.expressions.categories.FunctionElementExpression;
import com.verba.language.expressions.rvalue.simple.NumericExpression;
import com.verba.language.expressions.rvalue.simple.QuoteExpression;
import com.verba.language.expressions.statements.assignment.AssignmentStatementExpression;
import com.verba.language.expressions.statements.returns.ReturnStatementExpression;
import com.verba.language.facades.FunctionCallFacade;

/**
 * Created by sircodesalot on 14/9/21.
 */
public class VariableLifetimeGraph implements FunctionElementVisitor {
  private final VariableLifetimeMap lifetimes = new VariableLifetimeMap();
  private final FunctionDeclarationExpression function;

  public VariableLifetimeGraph(FunctionDeclarationExpression function) {
    this.function = function;

    buildGraph(function);
  }

  private void buildGraph(FunctionDeclarationExpression function) {
    BlockDeclarationExpression block = function.block();
    for (FunctionElementExpression expression : block.expressions().cast(FunctionElementExpression.class)) {
      expression.accept(this);
    }
  }

  public FunctionDeclarationExpression function() {
    return this.function;
  }

  public void visit(ReturnStatementExpression returnStatementExpression) {

  }

  @Override
  public void visit(AssignmentStatementExpression assignmentStatementExpression) {

  }

  @Override
  public void visit(QuoteExpression quoteExpression) {
    lifetimes.updateLifetime(quoteExpression);
  }

  @Override
  public void visit(NumericExpression numericExpression) {
    lifetimes.updateLifetime(numericExpression);
  }

  public void visit(NamedValueExpression namedValueExpression) {
    if (FunctionCallFacade.isFunctionCall(namedValueExpression)) {
      FunctionCallFacade call = new FunctionCallFacade(namedValueExpression);
      for (FunctionElementExpression declaration : call.primaryParameters().cast(FunctionElementExpression.class)) {
        declaration.accept(this);
      }
    }
  }

  public boolean containsVariable(VerbaExpression expression) {
    return this.lifetimes.containsLifetime(expression);
  }

  public VariableLifetime getVariableLifetime(VerbaExpression expression) {
    return this.lifetimes.getLifetime(expression);
  }

  public boolean isFirstOccurance(VerbaExpression expression) {
    VariableLifetime lifetime = this.lifetimes.getLifetime(expression);
    return lifetime.isFirstInstance(expression);
  }

  public boolean isLastOccurance(VerbaExpression expression) {
    VariableLifetime lifetime = this.lifetimes.getLifetime(expression);
    return lifetime.isLastOccurance(expression);
  }

}

