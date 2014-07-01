package main.java.verba.language.symbols.meta.decorators;

import main.java.verba.language.symbols.table.entries.SymbolTableEntry;

/**
 * Created by sircodesalot on 14-5-30.
 */
public class SymbolTableDecorator {
    private final SymbolTableEntry entry;

    public SymbolTableDecorator(SymbolTableEntry entry) {
        this.entry = entry;
    }

    protected <T> T getSingleMetadataEntry(Class<T> type) {
        return this.entry.metadata().ofType(type).single();
    }
}
