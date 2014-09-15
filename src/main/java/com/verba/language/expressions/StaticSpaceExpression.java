package com.verba.language.expressions;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.verba.language.ast.AstTreeFlattener;
import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.build.codepage.VerbaCodePage;
import com.verba.language.expressions.categories.SymbolTableExpression;
import com.verba.language.symbols.table.tables.GlobalSymbolTable;
import com.verba.language.symbols.table.tables.ScopedSymbolTable;
import com.verba.vblz.build.objectfile.SourceFilePathInfo;

/**
 * Created by sircodesalot on 14-5-14.
 */
public class StaticSpaceExpression extends VerbaExpression implements SymbolTableExpression {
  private GlobalSymbolTable symbolTable;
  private AstTreeFlattener allSubExpressions;
  private final QList<VerbaExpression> rootExpressions = new QList<>();

  public StaticSpaceExpression(Iterable<VerbaExpression> rootExpressions) {
    super(null, null);

    for (VerbaExpression expression : rootExpressions)
      this.rootExpressions.add(expression);

    this.update();
  }

  public StaticSpaceExpression(VerbaExpression... rootExpressions) {
    super(null, null);

    for (VerbaExpression expression : rootExpressions)
      this.rootExpressions.add(expression);

    this.update();
  }

  public StaticSpaceExpression(SourceFilePathInfo path) {
    this(VerbaCodePage.fromFile(null, path.absolutePath()));
  }

  public QIterable<VerbaExpression> rootLevelExpressions() {
    return this.rootExpressions;
  }

  public GlobalSymbolTable globalSymbolTable() {
    return this.symbolTable;
  }

  public AstTreeFlattener allSubExpressions() {
    return this.allSubExpressions;
  }

  public void update() {
    this.symbolTable = new GlobalSymbolTable(this);
    this.allSubExpressions = new AstTreeFlattener(this);
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void accept(ScopedSymbolTable symbolTable) {
    symbolTable.visit(this);
  }
}
