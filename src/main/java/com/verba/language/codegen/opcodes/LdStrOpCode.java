package com.verba.language.codegen.opcodes;

import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.registers.VirtualVariableSet;
import com.verba.language.codegen.rendering.OpCodeRenderer;

/**
 * Created by sircodesalot on 14/9/20.
 */
public class LdStrOpCode implements VerbajOpCode {
  private final VirtualVariable variable;
  private final String text;

  public LdStrOpCode(VirtualVariable variable, String text) {
    this.variable = variable;
    this.text = text;
  }

  @Override
  public int opNumber() { return 0xd1; }

  @Override
  public void render(OpCodeRenderer renderer) {
    renderer.writeOp(0xcd);
    renderer.writeInt16(text.length());
  }

  public String text() { return this.text; }
  public VirtualVariable variable() { return this.variable; }
}
