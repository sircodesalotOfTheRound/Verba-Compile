package main.java.verba.language.codegen.opcodes.opcodes.math;

import main.java.verba.language.codegen.opcodes.VMOp;
import main.java.verba.language.codegen.opcodes.datasegments.DataSegment;
import main.java.verba.language.codegen.opcodes.datasegments.OpCode;

/**
 * Created by sircodesalot on 14-4-29.
 */
public class MultOp implements OpCode {
    private final byte lregister;
    private final byte rregister;
    private final byte resultRegister;

    public MultOp(byte lhs, byte rhs, byte resultRegister) {
        this.lregister = lhs;
        this.rregister = rhs;
        this.resultRegister = resultRegister;
    }

    @Override
    public void render(DataSegment segment) {
        segment.addOpCode(VMOp.MULT);

        segment.add8(resultRegister);
        segment.add8(lregister);
        segment.add8(rregister);
    }

    @Override
    public VMOp opcode() {
        return VMOp.ADD;
    }
}
