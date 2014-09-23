package com.verba.language.codegen.opcodes;

import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.rendering.OpCodeRenderer;

/**
 * Created by sircodesalot on 14/9/22.
 */
public class StageArgOpCode implements VerbajOpCode {
  private int variableNumber;

  public StageArgOpCode(VirtualVariable variable) {
    this.variableNumber = variable.variableNumber();
  }

  @Override
  public int opNumber() { return 0x29; }

  @Override
  public String opName() { return "StgArg"; }

  @Override
  public void render(OpCodeRenderer renderer) {
    renderer.writeInt8("varnum", variableNumber);
  }
}
