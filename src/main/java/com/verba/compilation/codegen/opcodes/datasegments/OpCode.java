package main.java.verba.language.codegen.opcodes.datasegments;

import main.java.verba.language.codegen.opcodes.VMOp;

/**
 * Created by sircodesalot on 14-4-28.
 */
public interface OpCode {
    VMOp opcode();
    void render(DataSegment segment);
}
