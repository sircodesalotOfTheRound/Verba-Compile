package com.verba.language.ast.visitor;

import com.verba.language.build.codepage.VerbaCodePage;
import com.verba.language.expressions.StaticSpaceExpression;
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

/**
 * Created by sircodesalot on 14/9/12.
 */
public interface AstVisitor {
  void visit(BlockDeclarationExpression verbaExpressions);

  void visit(TraitDeclarationExpression traitDeclarationExpression);

  void visit(StaticSpaceExpression staticSpaceExpression);

  void visit(NamedValueExpression namedObjectDeclarationExpression);

  void visit(ClassDeclarationExpression classDeclarationExpression);

  void visit(FunctionDeclarationExpression functionDeclarationExpression);

  void visit(TaskDeclarationExpression taskDeclarationExpression);

  void visit(ArrayDeclarationExpression arrayDeclarationExpression);

  void visit(JsonExpression jsonExpression);

  void visit(TupleDeclarationExpression tupleDeclarationExpression);

  void visit(VerbaCodePage verbaCodePage);

  void visit(ReturnStatementExpression returnStatementExpression);

  void visit(SignatureDeclarationExpression signatureDeclarationExpression);

  void visit(QuoteExpression quoteExpression);

  void visit(AssignmentStatementExpression assignmentStatementExpression);

  void visit(NumericExpression expression);
}
