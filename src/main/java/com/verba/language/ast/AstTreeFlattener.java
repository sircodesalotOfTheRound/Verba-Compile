package com.verba.language.ast;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
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
import com.verba.language.expressions.statements.returns.ReturnStatementExpression;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Flattens a tree of expressions into a QList.
 */
public class AstTreeFlattener implements AstVisitor, Serializable, QIterable<VerbaExpression> {
  private final QList<VerbaExpression> expressions = new QList<>();

  public AstTreeFlattener(VerbaExpression root) {
    root.accept(this);
  }

  public QIterable<VerbaExpression> expressions() {
    return this.expressions;
  }

  @Override
  public Iterator<VerbaExpression> iterator() {
    return expressions.iterator();
  }

  private void add(VerbaExpression expression) {
    this.expressions.add(expression);
  }

  public void visit(TraitDeclarationExpression node) {
    add(node);
    this.visitAll(node.block());
  }

  public void visit(StaticSpaceExpression node) {
    add(node);
    this.visitAll(node.rootLevelExpressions());
  }

  public void visit(NamedValueExpression node) {
    add(node);

    if (node.hasTypeConstraint()) {
      VerbaExpression expression = (VerbaExpression)node.typeDeclaration();
      expression.accept(this);
    }
  }

  // Todo: Should this also read statements as well? Probabaly?

  public void visit(ClassDeclarationExpression classDeclaration) {
    add(classDeclaration);

    this.visitAll(classDeclaration.block());
  }

  public void visit(FunctionDeclarationExpression function) {
    add(function);

    this.visitAll(function.block());
  }

  public void visit(TaskDeclarationExpression task) {
    add(task);

    this.visitAll(task.block());
  }

  public void visit(ArrayDeclarationExpression array) {
    add(array);
    this.visitAll(array.items());
  }

  public void visit(JsonExpression jsonExpression) {
    add(jsonExpression);
    visitAll(jsonExpression.items());
  }

  public void visit(TupleDeclarationExpression tuple) {
    add(tuple);
    this.visitAll(tuple.items());
  }

  public void visit(BlockDeclarationExpression block) {
    add(block);
    this.visitAll(block);
  }

  public void visit(VerbaCodePage page) {
    add(page);
    this.visitAll(page.expressions());
  }

  @Override
  public void visit(ReturnStatementExpression returnStatementExpression) {
    add(returnStatementExpression);
  }

  @Override
  public void visit(SignatureDeclarationExpression signature) {
    add(signature);
  }

  public <T extends VerbaExpression> void visitAll(Iterable<T> expressions) {
    for (VerbaExpression expression : expressions) {
      expression.accept(this);
    }
  }

}
