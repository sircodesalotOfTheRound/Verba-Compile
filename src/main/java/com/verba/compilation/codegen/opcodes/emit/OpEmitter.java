package main.java.verba.language.codegen.opcodes.emit;

import main.java.verba.language.codegen.opcodes.datasegments.DataSegment;

/**
 * Created by sircodesalot on 14-6-11.
 */
public abstract class OpEmitter {
    private int startingRegister;

    public OpEmitter(int startingRegister) {
        this.startingRegister = startingRegister;
    }

    public int startingRegister() { return this.startingRegister; }

    public abstract void emit(DataSegment segment);
}
