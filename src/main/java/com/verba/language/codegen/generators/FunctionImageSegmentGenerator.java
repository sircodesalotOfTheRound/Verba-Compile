package com.verba.language.codegen.generators;

import com.javalinq.implementations.QList;
import com.verba.language.codegen.images.ImageSegment;
import com.verba.language.codegen.opcodes.VerbajOpCode;
import com.verba.language.expressions.block.BlockDeclarationExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedValueExpression;
import com.verba.language.expressions.categories.StatementExpression;
import com.verba.language.expressions.statements.assignment.AssignmentStatementExpression;
import com.verba.language.expressions.statements.returns.ReturnStatementExpression;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by sircodesalot on 14/9/19.
 */
public class FunctionImageSegmentGenerator {
  private final FunctionDeclarationExpression function;
  private QList<VerbajOpCode> opcodes = new QList<>();


  public FunctionImageSegmentGenerator(FunctionDeclarationExpression function) {
    this.function = function;

    buildImage(function);
  }

  private void buildImage(FunctionDeclarationExpression function) {
    BlockDeclarationExpression block = function.block();
    for (StatementExpression expression : block.expressions().cast(StatementExpression.class)) {
      expression.accept(this);
    }
  }

  public ImageSegment segment() { throw new NotImplementedException(); }
  public FunctionDeclarationExpression function() { return this.function; }

  public void visit(ReturnStatementExpression returnStatementExpression) {

  }

  public void visit(NamedValueExpression namedValueExpression) {

  }

  public void visit(AssignmentStatementExpression assignmentStatementExpression) {

  }
}
