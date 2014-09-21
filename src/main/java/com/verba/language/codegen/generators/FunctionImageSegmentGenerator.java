package com.verba.language.codegen.generators;

import com.javalinq.implementations.QList;
import com.verba.language.codegen.images.ImageSegment;
import com.verba.language.codegen.opcodes.LdStrOpCode;
import com.verba.language.codegen.opcodes.VerbajOpCode;
import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.registers.VirtualVariableSet;
import com.verba.language.expressions.block.BlockDeclarationExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedValueExpression;
import com.verba.language.expressions.categories.FunctionElementExpression;
import com.verba.language.expressions.rvalue.simple.QuoteExpression;
import com.verba.language.expressions.statements.assignment.AssignmentStatementExpression;
import com.verba.language.expressions.statements.returns.ReturnStatementExpression;
import com.verba.virtualmachine.VirtualMachineNativeTypes;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by sircodesalot on 14/9/19.
 */
public class FunctionImageSegmentGenerator {
  private final VirtualVariableSet variableSet;
  private final FunctionDeclarationExpression function;
  private QList<VerbajOpCode> opcodes = new QList<>();


  public FunctionImageSegmentGenerator(FunctionDeclarationExpression function) {
    this.variableSet = new VirtualVariableSet(20);
    this.function = function;

    buildImage(function);
  }

  private void buildImage(FunctionDeclarationExpression function) {
    BlockDeclarationExpression block = function.block();
    for (FunctionElementExpression expression : block.expressions().cast(FunctionElementExpression.class)) {
      expression.accept(this);
    }
  }

  public ImageSegment segment() { throw new NotImplementedException(); }
  public FunctionDeclarationExpression function() { return this.function; }

  public void visit(ReturnStatementExpression returnStatementExpression) {

  }

  public void visit(NamedValueExpression namedValueExpression) {
    for (FunctionElementExpression declaration : namedValueExpression
      .parameters()
      .first()
      .items()
      .cast(FunctionElementExpression.class))
    {
      declaration.accept(this);
    }
  }

  public void visit(AssignmentStatementExpression assignmentStatementExpression) {

  }

  public void visit(QuoteExpression quoteExpression) {
    VirtualVariable variable = variableSet.add(quoteExpression, VirtualMachineNativeTypes.UTF8);

    opcodes.add(new LdStrOpCode(variable, quoteExpression.representation()));

    VirtualVariable variableByExpression = variableSet.variableByExpression(quoteExpression);
    System.out.println(String.format("%s %s", variableByExpression.variableNumber(), quoteExpression.representation()));
  }
}
