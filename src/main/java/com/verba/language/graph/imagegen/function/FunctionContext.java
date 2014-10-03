package com.verba.language.graph.imagegen.function;

import com.javalinq.implementations.QList;
import com.verba.language.graph.imagegen.function.variables.VariableLifetimeGraph;
import com.verba.language.codegen.opcodes.VerbajOpCode;
import com.verba.language.codegen.registers.VirtualVariableSet;
import com.verba.language.expressions.StaticSpaceExpression;

/**
 * Created by sircodesalot on 14/10/3.
 */
public class FunctionContext {
  private final StaticSpaceExpression staticSpaceExpression;
  private final VirtualVariableSet variableSet;
  private final VariableLifetimeGraph lifetimeGraph;
  private final QList<VerbajOpCode> opcodes;

  public FunctionContext(StaticSpaceExpression staticSpaceExpression, VirtualVariableSet variableSet, VariableLifetimeGraph lifetimeGraph, QList<VerbajOpCode> opcodes) {
    this.staticSpaceExpression = staticSpaceExpression;
    this.variableSet = variableSet;
    this.lifetimeGraph = lifetimeGraph;
    this.opcodes = opcodes;
  }

  public StaticSpaceExpression staticSpaceExpression() { return this.staticSpaceExpression; }
  public VirtualVariableSet variableSet() { return this.variableSet; }
  public VariableLifetimeGraph lifetimeGraph() { return this.lifetimeGraph; }
  public QList<VerbajOpCode> opcodes() { return this.opcodes; }
}
