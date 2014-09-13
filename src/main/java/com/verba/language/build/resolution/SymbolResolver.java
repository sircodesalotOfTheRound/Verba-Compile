package com.verba.language.build.resolution;

import com.javalinq.interfaces.QIterable;
import com.verba.language.exceptions.CompilerException;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.categories.InvokableExpression;
import com.verba.language.expressions.categories.NamedDataDeclarationExpression;
import com.verba.language.expressions.categories.PolymorphicExpression;
import com.verba.language.expressions.categories.ResolvableTypeExpression;
import com.verba.language.expressions.statements.declaration.MutaDeclarationStatement;
import com.verba.language.expressions.statements.declaration.ValDeclarationStatement;
import com.verba.language.symbols.resolution.fields.VariableTypeResolver;
import com.verba.language.symbols.resolution.function.FunctionReturnTypeResolver;
import com.verba.language.symbols.resolution.polymorphic.PolymorphicResolver;
import com.verba.language.symbols.table.entries.SymbolTableEntry;
import com.verba.language.symbols.table.tables.GlobalSymbolTable;

/**
 * Resolves symbol table information about application symbols.
 */
public class SymbolResolver {
  private final GlobalSymbolTable symbolTable;
  private final PolymorphicResolver polymorphicResolver;
  private final VariableTypeResolver variableResolver;
  private final FunctionReturnTypeResolver functionResolver;


  public SymbolResolver(GlobalSymbolTable symbolTable) {
    this.symbolTable = symbolTable;
    this.polymorphicResolver = new PolymorphicResolver(symbolTable);
    this.variableResolver = new VariableTypeResolver(symbolTable);
    this.functionResolver = new FunctionReturnTypeResolver(symbolTable);
  }

  public void resolve(ResolvableTypeExpression expression) {
    expression.accept(this);
  }

  public void resolveAll() {
    QIterable<ResolvableTypeExpression> resolvableExpressions = this.symbolTable
      .entries()
      .map(SymbolTableEntry::instance)
      .ofType(ResolvableTypeExpression.class);

    for (ResolvableTypeExpression expression : resolvableExpressions) {
      expression.accept(this);
    }
  }

  public void visit(ValDeclarationStatement valDeclarationStatement) {
    variableResolver.resolve(valDeclarationStatement);
  }

  public void visit(MutaDeclarationStatement mutaDeclarationStatement) {
    variableResolver.resolve(mutaDeclarationStatement);
  }

  public void visit(FunctionDeclarationExpression function) {
    functionResolver.resolve(function);
  }

  public static void resolveNames(GlobalSymbolTable globalSymbolTable) {
    new SymbolResolver(globalSymbolTable).resolveAll();
  }

}
