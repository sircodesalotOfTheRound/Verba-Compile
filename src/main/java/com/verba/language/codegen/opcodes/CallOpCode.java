package com.verba.language.codegen.opcodes;

import com.javalinq.implementations.QList;
import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.rendering.OpCodeRenderer;

/**
 * Created by sircodesalot on 14/9/19.
 */
public class CallOpCode extends VerbajOpCode {
  public String function;
  public Iterable<VirtualVariable> variables;

  public CallOpCode(String function, Iterable<VirtualVariable> variables) {
    this.function = function;
    this.variables = variables;
  }

  public CallOpCode(String function) {
    this(function, new QList<>());
  }

  @Override
  public void render(OpCodeRenderer renderer) {
    renderer.writeOp(0xcc);
    renderer.writeString(function);
  }

  public String function() {
    return this.function;
  }
}
