package com.verba.language.ast;

import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedValueExpression;
import com.verba.language.expressions.rvalue.simple.NumericExpression;
import com.verba.language.expressions.rvalue.simple.QuoteExpression;
import com.verba.language.expressions.statements.assignment.AssignmentStatementExpression;
import com.verba.language.expressions.statements.returns.ReturnStatementExpression;

/**
 * Created by sircodesalot on 14/9/22.
 */
public interface FunctionElementVisitor {
  void visit(NamedValueExpression namedValueExpression);

  void visit(ReturnStatementExpression returnStatementExpression);

  void visit(AssignmentStatementExpression assignmentStatementExpression);

  void visit(QuoteExpression quoteExpression);

  void visit(NumericExpression numericExpression);

  void visit(FunctionDeclarationExpression functionDeclarationExpression);
}
