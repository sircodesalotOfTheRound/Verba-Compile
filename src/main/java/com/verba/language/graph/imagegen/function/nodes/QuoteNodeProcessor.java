package com.verba.language.graph.imagegen.function.nodes;

import com.javalinq.implementations.QList;
import com.verba.language.graph.imagegen.function.FunctionContext;
import com.verba.language.graph.imagegen.function.variables.VariableLifetime;
import com.verba.language.graph.imagegen.function.variables.VariableLifetimeGraph;
import com.verba.language.codegen.opcodes.LdStrOpCode;
import com.verba.language.codegen.opcodes.VerbajOpCode;
import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.registers.VirtualVariableSet;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.rvalue.simple.QuoteExpression;
import com.verba.virtualmachine.VirtualMachineNativeTypes;

/**
 * Created by sircodesalot on 14/10/3.
 */
public class QuoteNodeProcessor {
  private final FunctionContext context;

  public QuoteNodeProcessor(FunctionContext context) {
    this.context = context;
  }

  public void process(QuoteExpression expression) {
    VariableLifetime variableLifetime = context.getVariableLifetime(expression);

    // If this is the first time seeing this variable, add it.
    if (variableLifetime.isFirstInstance(expression)) {
      VirtualVariable variable = context.addVariable(expression, VirtualMachineNativeTypes.UTF8);
      context.addOpCode(new LdStrOpCode(variable, expression.innerText()));
    }
  }
}
