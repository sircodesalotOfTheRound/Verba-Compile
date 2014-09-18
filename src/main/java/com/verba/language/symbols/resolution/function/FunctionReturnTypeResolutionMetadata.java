package com.verba.language.symbols.resolution.function;

import com.verba.language.ast.AstTreeFlattener;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.block.BlockDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedValueExpression;
import com.verba.language.expressions.categories.*;
import com.verba.language.expressions.statements.returns.ReturnStatementExpression;
import com.verba.language.symbols.meta.NestedSymbolTableMetadata;
import com.verba.language.symbols.meta.interfaces.SymbolTableMetadata;
import com.verba.language.symbols.meta.interfaces.SymbolTypeMetadata;
import com.verba.language.symbols.resolution.fields.VariableNameSearch;
import com.verba.language.symbols.resolution.fields.VariableTypeResolutionMetadata;
import com.verba.language.symbols.resolution.fields.VariableTypeResolver;
import com.verba.language.symbols.resolution.interfaces.SymbolResolutionInfo;
import com.verba.language.symbols.table.entries.SymbolTableEntry;
import com.verba.language.symbols.table.tables.GlobalSymbolTable;
import com.verba.language.symbols.table.tables.ScopedSymbolTable;
import com.verba.virtualmachine.VirtualMachineNativeTypes;

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

  private TypeDeclarationExpression getTypeFromExpression(ReturnStatementExpression statement) {
    RValueExpression value = statement.value();

    if (value instanceof NativeTypeExpression) {
      return ((NativeTypeExpression) value).nativeTypeDeclaration();
    }

    if (value instanceof NamedValueExpression) {
      String variableName = ((NamedValueExpression) value).name();
      VariableNameSearch search = new VariableNameSearch(symbolTable, scope, variableName);

      return search.resolvedType();
    }

    // Todo: make this more robust
    return VirtualMachineNativeTypes.UNIT_EXPRESSION;
  }

  private TypeDeclarationExpression scanFunction(BlockDeclarationExpression block) {
    AstTreeFlattener scopeTree = new AstTreeFlattener(block);
    for (ReturnStatementExpression statement : scopeTree.ofType(ReturnStatementExpression.class)) {
      if (statement.hasValue()) {
        return getTypeFromExpression(statement);
      }
    }

    return VirtualMachineNativeTypes.UNIT_EXPRESSION;
  }

  // TODO: Named resolution needs to be abstracted out into a class.
  public TypeDeclarationExpression resolveFromVariableName(VerbaExpression value) {
    NamedValueExpression variable = (NamedValueExpression) value;
    String localName = variable.name();

    if (this.scope.containsKey(localName)) {
      SymbolTableEntry local = this.scope.get(localName).single();
      VariableTypeResolver variableTypeResolver = new VariableTypeResolver(symbolTable);
      VariableTypeResolutionMetadata resolution = variableTypeResolver.resolve((NamedAndTypedExpression) local.instance());

      return resolution.symbolType();
    }

    return null;
  }

  @Override
  public TypeDeclarationExpression symbolType() {
    return this.type;
  }
}
