package main.java.verba.language.symbols.resolution.metatags;

import main.java.verba.language.symbols.resolution.interfaces.SymbolResolutionInfo;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;

/**
 * Created by sircodesalot on 14-5-16.
 */
public class MetaTagResolutionInfo implements SymbolResolutionInfo {
    public SymbolTableEntry entry;

    public MetaTagResolutionInfo(SymbolTableEntry entry) {
        this.entry = entry;
    }

    public SymbolTableEntry targetEntry() {
        return this.entry;
    }
}
