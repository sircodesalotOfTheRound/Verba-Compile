package com.verba.language.codegen.opcodes;

import com.verba.language.codegen.registers.VirtualVariable;
import com.verba.language.codegen.rendering.OpCodeRenderer;

/**
 * Created by sircodesalot on 14/9/23.
 */
public class BoxOpCode implements VerbajOpCode {
  private final VirtualVariable source;
  private final VirtualVariable destination;

  public BoxOpCode(VirtualVariable source, VirtualVariable destination) {
    this.source = source;
    this.destination = destination;
  }

  @Override
  public int opNumber() { return 0x31; }

  @Override
  public String opName() { return "Box"; }

  @Override
  public void render(OpCodeRenderer renderer) {
    renderer.writeInt8("source", source.variableNumber());
    renderer.writeInt8("destination", destination.variableNumber());
  }
}
