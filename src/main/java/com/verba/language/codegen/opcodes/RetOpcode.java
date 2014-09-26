package com.verba.language.codegen.opcodes;

import com.verba.language.codegen.rendering.OpCodeRenderer;

/**
 * Created by sircodesalot on 14/9/19.
 */
public class RetOpCode implements VerbajOpCode {
  @Override
  public int opNumber() { return 0xc7; }

  @Override
  public String opName() { return "Ret"; }

  @Override
  public void render(OpCodeRenderer renderer) { /* The op will automatically be written*/ }
}