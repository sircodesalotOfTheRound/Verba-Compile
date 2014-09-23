package com.verba.language.codegen.opcodes;

import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.rendering.OpCodeRenderer;

/**
 * Created by sircodesalot on 14/9/20.
 */
public class Lduint64OpCode implements VerbajOpCode {
  private final VirtualVariable variable;
  private final String text;

  public Lduint64OpCode(VirtualVariable variable, String text) {
    this.variable = variable;
    this.text = text;
  }

  @Override
  public int opNumber() { return 0xd1; }

  @Override
  public String opName() { return "LdStr"; }

  @Override
  public void render(OpCodeRenderer renderer) {
    renderer.writeInt8("varnum", variable.variableNumber());
    renderer.writeString("text", text);
  }

  public String text() { return this.text; }
  public VirtualVariable variable() { return this.variable; }
}
