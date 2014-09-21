package com.verba.language.codegen.opcodes;

import com.verba.language.codegen.rendering.OpCodeRenderer;

/**
 * Created by sircodesalot on 14/9/19.
 */
public interface VerbajOpCode {
  int opNumber();
  void render(OpCodeRenderer renderer);
}
