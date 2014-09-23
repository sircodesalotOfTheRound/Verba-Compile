package com.verba.language.codegen.rendering;

import com.verba.language.codegen.opcodes.VerbajOpCode;

/**
 * Created by sircodesalot on 14/9/19.
 */
public interface OpCodeRenderer {
  void writeInt8(String label, int value);
  void writeInt16(String label, int value);
  void writeInt32(String label, int value);
  void writeInt64(String label, long value);

  void writeString(String label, String value);
}
