package main.java.verba.language.symbols.resolution.polymorphic;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.PolymorphicExpression;
import main.java.verba.language.symbols.resolution.interfaces.SymbolResolver;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;
import main.java.verba.language.symbols.table.tables.GlobalSymbolTable;

/**
 * Created by sircodesalot on 14-5-20.
 */
public class PolymorphicResolver implements SymbolResolver<PolymorphicExpression, PolymorphicResolutionMetadata> {
    private final GlobalSymbolTable symbolTable;

    public PolymorphicResolver(GlobalSymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public PolymorphicResolutionMetadata resolveBinding(SymbolTableEntry entry) {
        // If the metadata has already been built for this class, then just return it.
        if (entry.metadata().ofType(PolymorphicResolutionMetadata.class).any()) {
            return entry.metadata().ofType(PolymorphicResolutionMetadata.class).single();
        }

        // Add the resolution information as metadata for easy access later.
        PolymorphicResolutionMetadata metadata = new PolymorphicResolutionMetadata(this.symbolTable, entry);
        entry.addMetadata(metadata);

        // Return the member name resolution information
        return metadata;
    }

    @Override
    public PolymorphicResolutionMetadata resolve(PolymorphicExpression declaration) {
        SymbolTableEntry entry = this.symbolTable.getByInstance((VerbaExpression) declaration);
        return this.resolveBinding(entry);
    }
}
