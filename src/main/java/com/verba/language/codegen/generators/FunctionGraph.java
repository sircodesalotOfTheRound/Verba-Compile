package com.verba.language.codegen.generators;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.verba.language.ast.FunctionElementVisitor;
import com.verba.language.codegen.function.VariableLifetime;
import com.verba.language.codegen.function.VariableLifetimeGraph;
import com.verba.language.codegen.opcodes.*;
import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.registers.VirtualVariableSet;
import com.verba.language.codegen.rendering.functions.MemoryStreamFunctionRenderer;
import com.verba.language.codegen.rendering.images.ObjectImage;
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
import com.verba.virtualmachine.VirtualMachineNativeTypes;

/**
 * Created by sircodesalot on 14/9/19.
 */
public class FunctionGraph implements FunctionElementVisitor {
  private final VirtualVariableSet variableSet;
  private final FunctionDeclarationExpression function;
  private final VariableLifetimeGraph lifetimeGraph;
  private QList<VerbajOpCode> opcodes = new QList<>();


  public FunctionGraph(FunctionDeclarationExpression function) {
    this.variableSet = new VirtualVariableSet(20);
    this.function = function;
    this.lifetimeGraph = new VariableLifetimeGraph(function);

    System.out.println(function.text());
    System.out.println();

    buildImage(function);
  }

  private void buildImage(FunctionDeclarationExpression function) {
    BlockDeclarationExpression block = function.block();
    for (FunctionElementExpression expression : block.expressions().cast(FunctionElementExpression.class)) {
      expression.accept(this);
    }

    closeOutFunction();
  }

  private void closeOutFunction() {
    // If function doesn't end with return, put one there.
    if (opcodes.any() && !(opcodes.last() instanceof RetOpCode)) {
      opcodes.add(new RetOpCode());
    }

    // Also close out the function.
    opcodes.add(new EndFunctionOpCode());
  }

  public ObjectImage createImage() {
    return new MemoryStreamFunctionRenderer(this);
  }

  public FunctionDeclarationExpression function() { return this.function; }

  public void visit(ReturnStatementExpression returnStatementExpression) {
    opcodes.add(new RetOpCode());
  }

  public void visit(NamedValueExpression namedValueExpression) {
    if (FunctionCallFacade.isFunctionCall(namedValueExpression)) {
      visitMethodCall(new FunctionCallFacade(namedValueExpression));
    }
  }

  private void visitMethodCall(FunctionCallFacade call) {
      QIterable<FunctionElementExpression> parametersAsFunctionElements
        = call.primaryParameters().cast(FunctionElementExpression.class);

      for (FunctionElementExpression declaration : parametersAsFunctionElements) {
        declaration.accept(this);
      }

      for (VerbaExpression expression : call.primaryParameters()) {
        VirtualVariable variable = this.variableSet.variableByExpression(expression);
        opcodes.add(new StageArgOpCode(variable));

        if (this.lifetimeGraph.isLastOccurance(expression)) {
          // TODO: This is broken.
          //this.variableSet.expireVariable(variable);
        }
      }

      opcodes.add(new CallOpCode(call.functionName()));
  }

  public void visit(AssignmentStatementExpression assignmentStatementExpression) {

  }

  public void visit(NumericExpression expression) {
    VariableLifetime variableLifetime = lifetimeGraph.getVariableLifetime(expression);

    if (variableLifetime.isFirstInstance(expression)) {
      VirtualVariable source = variableSet.add(expression, VirtualMachineNativeTypes.UTF8);
      VirtualVariable destination = variableSet.add(expression, VirtualMachineNativeTypes.BOX_UINT64);

      opcodes.add(new LdUint64OpCode(source, expression.asLong()));
      opcodes.add(new BoxOpCode(source, destination));
    }
  }

  public void visit(QuoteExpression quoteExpression) {
    VariableLifetime variableLifetime = lifetimeGraph.getVariableLifetime(quoteExpression);

    // If this is the first time seeing this variable, add it.
    if (variableLifetime.isFirstInstance(quoteExpression)) {
      VirtualVariable variable = variableSet.add(quoteExpression, VirtualMachineNativeTypes.UTF8);
      opcodes.add(new LdStrOpCode(variable, quoteExpression.innerText()));
    }
  }

  public Iterable<VerbajOpCode> opcodes() { return this.opcodes; }

  public String name() { return this.function.name(); }
}
