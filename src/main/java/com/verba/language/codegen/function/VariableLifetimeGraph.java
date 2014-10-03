package com.verba.language.codegen.function;

import com.verba.language.ast.FunctionElementVisitor;
import com.verba.language.ast.visitor.AstVisitable;
import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.build.codepage.VerbaCodePage;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.block.BlockDeclarationExpression;
import com.verba.language.expressions.blockheader.classes.ClassDeclarationExpression;
import com.verba.language.expressions.blockheader.classes.TraitDeclarationExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.blockheader.functions.SignatureDeclarationExpression;
import com.verba.language.expressions.blockheader.functions.TaskDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedValueExpression;
import com.verba.language.expressions.containers.array.ArrayDeclarationExpression;
import com.verba.language.expressions.containers.json.JsonExpression;
import com.verba.language.expressions.containers.tuple.TupleDeclarationExpression;
import com.verba.language.expressions.rvalue.simple.NumericExpression;
import com.verba.language.expressions.rvalue.simple.QuoteExpression;
import com.verba.language.expressions.statements.assignment.AssignmentStatementExpression;
import com.verba.language.expressions.statements.returns.ReturnStatementExpression;
import com.verba.language.facades.FunctionCallFacade;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by sircodesalot on 14/9/21.
 */
public class VariableLifetimeGraph implements AstVisitor {
  private final VariableLifetimeMap lifetimes = new VariableLifetimeMap();
  private final FunctionDeclarationExpression function;

  public VariableLifetimeGraph(FunctionDeclarationExpression function) {
    this.function = function;

    buildGraph(function);
  }

  private void buildGraph(FunctionDeclarationExpression function) {
    BlockDeclarationExpression block = function.block();
    for (AstVisitable expression : block.expressions().cast(AstVisitable.class)) {
      expression.accept(this);
    }
  }

  public FunctionDeclarationExpression function() {
    return this.function;
  }

  public void visit(ReturnStatementExpression returnStatementExpression) {

  }

  @Override
  public void visit(SignatureDeclarationExpression signatureDeclarationExpression) {
    throw new NotImplementedException();
  }

  @Override
  public void visit(AssignmentStatementExpression assignmentStatementExpression) {
    throw new NotImplementedException();
  }

  @Override
  public void visit(QuoteExpression quoteExpression) {
    lifetimes.updateLifetime(quoteExpression);
  }

  @Override
  public void visit(NumericExpression numericExpression) {
    lifetimes.updateLifetime(numericExpression);
  }

  @Override
  public void visit(FunctionDeclarationExpression functionDeclarationExpression) {
    // Todo: currently no op.
  }

  @Override
  public void visit(TaskDeclarationExpression taskDeclarationExpression) {
    throw new NotImplementedException();

  }

  @Override
  public void visit(ArrayDeclarationExpression arrayDeclarationExpression) {
    throw new NotImplementedException();

  }

  @Override
  public void visit(JsonExpression jsonExpression) {
    throw new NotImplementedException();
  }

  @Override
  public void visit(TupleDeclarationExpression tupleDeclarationExpression) {
    throw new NotImplementedException();

  }

  @Override
  public void visit(VerbaCodePage verbaCodePage) {
    throw new NotImplementedException();

  }

  @Override
  public void visit(BlockDeclarationExpression verbaExpressions) {
    throw new NotImplementedException();

  }

  @Override
  public void visit(TraitDeclarationExpression traitDeclarationExpression) {
    throw new NotImplementedException();
  }

  @Override
  public void visit(StaticSpaceExpression staticSpaceExpression) {
    throw new NotImplementedException();
  }

  public void visit(NamedValueExpression namedValueExpression) {
    if (FunctionCallFacade.isFunctionCall(namedValueExpression)) {
      FunctionCallFacade call = new FunctionCallFacade(namedValueExpression);
      for (AstVisitable declaration : call.primaryParameters().cast(AstVisitable.class)) {
        declaration.accept(this);
      }
    }
  }

  @Override
  public void visit(ClassDeclarationExpression classDeclarationExpression) {
    throw new NotImplementedException();
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

