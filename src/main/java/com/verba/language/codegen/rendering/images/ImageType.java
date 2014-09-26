package com.verba.language.codegen.rendering.images;

import com.verba.language.exceptions.CompilerException;

/**
 * Created by sircodesalot on 14/9/26.
 */
public enum ImageType {
  FUNCTION;

  public byte[] asByteArray() {
    final byte[] functionCode = new byte[] { 0x11, 0x11 };

    switch (this) {
      case FUNCTION:
        return functionCode;
    }

    throw new CompilerException("Invalid image type");
  }
}
