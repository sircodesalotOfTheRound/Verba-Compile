package main.java.verba.language.symbols.meta.decorators;

import main.java.verba.language.expressions.block.BlockDeclarationExpression;
import main.java.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import main.java.verba.language.expressions.blockheader.generic.GenericTypeListExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.expressions.containers.tuple.TupleDeclarationExpression;
import main.java.verba.language.symbols.meta.NestedSymbolTableMetadata;
import main.java.verba.language.symbols.resolution.function.FunctionReturnTypeResolutionMetadata;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;
import main.java.verba.language.symbols.table.tables.ScopedSymbolTable;

/**
 * Created by sircodesalot on 14-5-29.
 */
public class FunctionSymbol extends SymbolTableDecorator {

    private final FunctionDeclarationExpression function;
    private final SymbolTableEntry entry;
    private final ScopedSymbolTable scopedSymbolTable;
    private final TypeDeclarationExpression returnType;

    public FunctionSymbol(SymbolTableEntry entry) {
        super(entry);

        this.entry = entry;
        this.function = (FunctionDeclarationExpression) entry.instance();
        this.scopedSymbolTable = super.getSingleMetadataEntry(NestedSymbolTableMetadata.class).symbolTable();
        this.returnType = getReturnType(entry);
    }

    private TypeDeclarationExpression getReturnType(SymbolTableEntry entry) {
        FunctionReturnTypeResolutionMetadata metadata = entry.metadata().ofType(FunctionReturnTypeResolutionMetadata.class).single();
        return metadata.symbolType();
    }

    public ScopedSymbolTable scope() {
        return this.scopedSymbolTable;
    }

    public FunctionDeclarationExpression function() {
        return this.function;
    }

    public SymbolTableEntry entry() {
        return this.entry;
    }

    public TypeDeclarationExpression returnType() {
        return this.returnType;
    }

    public TupleDeclarationExpression parameters() {
        return this.function.parameters().single();
    }

    public GenericTypeListExpression genericParameters() {
        return this.function.genericParameters();
    }

    public boolean hasParameters() {
        return this.function.hasParameters();
    }

    public boolean hasGenericParameters() {
        return this.function.hasGenericParameters();
    }

    public BlockDeclarationExpression block() {
        return function.block();
    }

    public String name() {
        return function.name();
    }

    public boolean isClosure() {
        return false;
    }

    public String fqn() {
        return this.entry.fqn();
    }
}
