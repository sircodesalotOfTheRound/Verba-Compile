package com.verba.language.test.typeresolve;

import com.sun.beans.TypeResolver;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.statements.declaration.MutaDeclarationStatement;
import com.verba.language.expressions.statements.declaration.ValDeclarationStatement;
import com.verba.language.symbols.resolution.fields.VariableTypeResolutionMetadata;
import com.verba.language.symbols.table.entries.SymbolTableEntry;
import com.verba.language.symbols.table.tables.GlobalSymbolTable;
import com.verba.language.test.tools.TestFileLoader;
import org.junit.Test;

/**
 * Created by sircodesalot on 14/9/12.
 */
public class DerivedTypeResolution {

  @Test
  public void testTypesDerivedFromParameter() {
    StaticSpaceExpression codeFile = TestFileLoader.TYPE_RESOLUTION_TESTS;

    FunctionDeclarationExpression typesDerivedFromParameter = codeFile.allSubExpressions()
      .ofType(FunctionDeclarationExpression.class)
      .singleOrNull(function -> function.name().equals("typesDerivedFromParameter"));

    GlobalSymbolTable symbolTable = codeFile.globalSymbolTable();
    symbolTable.resolveSymbolNames();

    SymbolTableEntry first = symbolTable.getByFqn("typesDerivedFromParameter.derivedFirst").single();
    SymbolTableEntry second = symbolTable.getByFqn("typesDerivedFromParameter.derivedSecond").single();

    VariableTypeResolutionMetadata firstMeta = first.metadata().ofType(VariableTypeResolutionMetadata.class).single();
    VariableTypeResolutionMetadata secondMeta = second.metadata().ofType(VariableTypeResolutionMetadata.class).single();

    assert(firstMeta.symbolType().representation().equals("string"));
    assert(secondMeta.symbolType().representation().equals("uint32"));
  }
}
