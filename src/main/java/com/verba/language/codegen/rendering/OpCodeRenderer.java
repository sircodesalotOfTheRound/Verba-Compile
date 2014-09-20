package com.verba.language.codegen.rendering;

/**
 * Created by sircodesalot on 14/9/19.
 */
public interface OpCodeRenderer {
  void writeOp(int value);
  void writeInt8(int value);
  void writeInt16(int value);
  void writeInt32(int value);
  void writeInt64(int value);

  void writeString(String value);
}
