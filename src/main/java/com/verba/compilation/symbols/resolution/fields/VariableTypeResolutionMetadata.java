package main.java.verba.language.symbols.resolution.fields;

import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.symbols.meta.interfaces.SymbolTableMetadata;
import main.java.verba.language.symbols.meta.interfaces.SymbolTypeMetadata;
import main.java.verba.language.symbols.resolution.interfaces.SymbolResolutionInfo;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;

/**
 * Created by sircodesalot on 14-5-22.
 */
public class VariableTypeResolutionMetadata implements SymbolTableMetadata, SymbolTypeMetadata, SymbolResolutionInfo {
    private final SymbolTableEntry entry;
    private final TypeDeclarationExpression type;

    public VariableTypeResolutionMetadata(SymbolTableEntry entry, TypeDeclarationExpression typeDeclarationExpression) {
        this.entry = entry;
        this.type = typeDeclarationExpression;
    }

    public SymbolTableEntry symbolTableEntry() {
        return this.entry;
    }

    @Override
    public TypeDeclarationExpression symbolType() {
        return this.type;
    }
}
