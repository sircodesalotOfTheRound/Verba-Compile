package main.java.verba.language.symbols.resolution.function;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.block.BlockDeclarationExpression;
import main.java.verba.language.expressions.blockheader.varname.VarNameDeclarationExpression;
import main.java.verba.language.expressions.categories.InvokableExpression;
import main.java.verba.language.expressions.categories.NamedDataDeclarationExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.expressions.statements.returns.ReturnStatementExpression;
import main.java.verba.language.symbols.meta.NestedSymbolTableMetadata;
import main.java.verba.language.symbols.meta.interfaces.SymbolTableMetadata;
import main.java.verba.language.symbols.meta.interfaces.SymbolTypeMetadata;
import main.java.verba.language.symbols.resolution.fields.VariableTypeResolutionMetadata;
import main.java.verba.language.symbols.resolution.fields.VariableTypeResolver;
import main.java.verba.language.symbols.resolution.interfaces.SymbolResolutionInfo;
import main.java.verba.language.symbols.table.entries.SymbolTableEntry;
import main.java.verba.language.symbols.table.tables.GlobalSymbolTable;
import main.java.verba.language.symbols.table.tables.ScopedSymbolTable;
import main.java.verba.virtualmachine.VirtualMachineNativeTypes;

/**
 * Created by sircodesalot on 14-5-25.
 */
public class FunctionReturnTypeResolutionMetadata implements SymbolResolutionInfo, SymbolTypeMetadata, SymbolTableMetadata {
    private final GlobalSymbolTable symbolTable;
    private final ScopedSymbolTable scope;

    private TypeDeclarationExpression type;

    public FunctionReturnTypeResolutionMetadata(GlobalSymbolTable symbolTable, InvokableExpression expression) {
        this.symbolTable = symbolTable;
        this.scope = getScope(symbolTable, expression);

        if (expression.hasTypeConstraint())
            this.type = expression.typeDeclaration();
        else
            this.type = this.scanFunction(expression.block());
    }

    private ScopedSymbolTable getScope(GlobalSymbolTable symbolTable, InvokableExpression expression) {
        SymbolTableEntry entry = symbolTable.getByInstance((VerbaExpression) expression);
        NestedSymbolTableMetadata metadata = entry.metadata().ofType(NestedSymbolTableMetadata.class).single();

        return metadata.symbolTable();
    }

    private TypeDeclarationExpression scanFunction(BlockDeclarationExpression block) {
        for (VerbaExpression expression : block.expressions()) {
            if (expression instanceof ReturnStatementExpression) {
                ReturnStatementExpression returnStatement = (ReturnStatementExpression) expression;

                if (returnStatement.hasValue()) {
                    VerbaExpression value = (VerbaExpression) returnStatement.value();
                    if (VirtualMachineNativeTypes.isVirtualMachineType((VerbaExpression) returnStatement.value())) {
                        return VirtualMachineNativeTypes.getTypeFromInstance((VerbaExpression) returnStatement.value());

                    } else if (value instanceof VarNameDeclarationExpression) {
                        return resolveFromVariableName(value);
                    }
                }
            }
        }

        return VirtualMachineNativeTypes.UNIT_EXPRESSION;
    }

    // TODO: Named resolution needs to be abstracted out into a class.
    public TypeDeclarationExpression resolveFromVariableName(VerbaExpression value) {
        VarNameDeclarationExpression variable = (VarNameDeclarationExpression) value;
        String localName = variable.name();

        if (this.scope.containsKey(localName)) {
            SymbolTableEntry local = this.scope.get(localName).single();
            VariableTypeResolver variableTypeResolver = new VariableTypeResolver(symbolTable);
            VariableTypeResolutionMetadata resolution = variableTypeResolver.resolve((NamedDataDeclarationExpression) local.instance());

            return resolution.symbolType();
        }

        return null;
    }

    @Override
    public TypeDeclarationExpression symbolType() {
        return this.type;
    }
}
