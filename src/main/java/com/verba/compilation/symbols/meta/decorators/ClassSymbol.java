package main.java.verba.language.symbols.meta.decorators;

import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.blockheader.classes.ClassDeclarationExpression;
import main.java.verba.language.expressions.blockheader.generic.GenericTypeListExpression;
import main.java.verba.language.expressions.categories.PolymorphicExpression;
import main.java.verba.language.expressions.containers.tuple.TupleDeclarationExpression;
import main.java.verba.language.symbols.meta.NestedSymbolTableMetadata;
import main.java.verba.language.symbols.resolution.polymorphic.PolymorphicResolutionMetadata;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;
import main.java.verba.language.symbols.table.tables.ScopedSymbolTable;

/**
 * Created by sircodesalot on 14-5-30.
 */
public class ClassSymbol extends SymbolTableDecorator {
    private final SymbolTableEntry entry;
    private final ClassDeclarationExpression classDeclaration;
    private final ScopedSymbolTable scope;
    private final PolymorphicResolutionMetadata polymorphicMembers;

    public ClassSymbol(SymbolTableEntry entry) {
        super(entry);

        this.entry = entry;
        this.classDeclaration = (ClassDeclarationExpression) entry.instance();
        this.scope = super.getSingleMetadataEntry(NestedSymbolTableMetadata.class).symbolTable();
        this.polymorphicMembers = super.getSingleMetadataEntry(PolymorphicResolutionMetadata.class);
    }

    public ClassDeclarationExpression classDeclaration() {
        return this.classDeclaration;
    }

    public boolean hasTraits() {
        return this.classDeclaration.hasTraits();
    }

    public QIterable<PolymorphicExpression> traits() {
        return this.classDeclaration.traits().cast(PolymorphicExpression.class);
    }

    public boolean hasGenericParameters() {
        return this.classDeclaration.hasGenericParameters();
    }

    public GenericTypeListExpression genericParameters() {
        return this.classDeclaration.genericParameters();
    }

    public boolean isInline() {
        return this.classDeclaration.isInlineClass();
    }

    public TupleDeclarationExpression inlineParameters() {
        return this.classDeclaration.inlineParameters().single();
    }

    public ScopedSymbolTable scope() {
        return this.scope;
    }

    public SymbolTableEntry entry() {
        return this.entry;
    }


}
