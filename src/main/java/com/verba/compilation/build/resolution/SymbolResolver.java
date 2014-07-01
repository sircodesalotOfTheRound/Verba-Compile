package main.java.verba.language.build.resolution;

import main.java.verba.language.exceptions.CompilerException;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.InvokableExpression;
import main.java.verba.language.expressions.categories.NamedDataDeclarationExpression;
import main.java.verba.language.expressions.categories.PolymorphicExpression;
import main.java.verba.language.symbols.resolution.fields.VariableTypeResolver;
import main.java.verba.language.symbols.resolution.function.FunctionReturnTypeResolver;
import main.java.verba.language.symbols.resolution.polymorphic.PolymorphicResolver;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;
import main.java.verba.language.symbols.table.tables.GlobalSymbolTable;

/**
 * Resolves symbol table information about application symbols.
 */
public class SymbolResolver {
    private final PolymorphicResolver polymorphicResolver;
    private final VariableTypeResolver variableResolver;
    private final FunctionReturnTypeResolver functionResolver;


    public SymbolResolver(GlobalSymbolTable symbolTable) {
        this.polymorphicResolver = new PolymorphicResolver(symbolTable);
        this.variableResolver = new VariableTypeResolver(symbolTable);
        this.functionResolver = new FunctionReturnTypeResolver(symbolTable);

        this.resolveAll(symbolTable);
    }

    private void resolveAll(GlobalSymbolTable symbolTable) {
        for (SymbolTableEntry entry : symbolTable.entries()) {
            VerbaExpression expression = entry.instance();

            if (expression instanceof InvokableExpression)
                this.functionResolver.resolve((InvokableExpression) expression);

            else if (expression instanceof PolymorphicExpression)
                this.polymorphicResolver.resolve((PolymorphicExpression) expression);

            else if (expression instanceof NamedDataDeclarationExpression)
                this.variableResolver.resolve((NamedDataDeclarationExpression) expression);

            else throw new CompilerException("%s is not a valid resolvable type", expression);
        }
    }


}
