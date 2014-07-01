package main.java.verba.language.symbols.resolution.fqn;

import main.java.verba.language.symbols.resolution.interfaces.SymbolResolutionInfo;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;

/**
 * Created by sircodesalot on 14-5-16.
 */
public class MemberResolutionInfo implements SymbolResolutionInfo {
    private final SymbolTableEntry entry;

    public MemberResolutionInfo(SymbolTableEntry entry) {
        this.entry = entry;
    }

}
