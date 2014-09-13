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

    GlobalSymbolTable symbolTable = codeFile.globalSymbolTable();
    symbolTable.resolveSymbolNames();

    SymbolTableEntry first = symbolTable.getByFqn("typesDerivedFromParameter.derivedFirst").single();
    SymbolTableEntry second = symbolTable.getByFqn("typesDerivedFromParameter.derivedSecond").single();

    VariableTypeResolutionMetadata firstMeta = first.metadata().ofType(VariableTypeResolutionMetadata.class).single();
    VariableTypeResolutionMetadata secondMeta = second.metadata().ofType(VariableTypeResolutionMetadata.class).single();

    assert(firstMeta.symbolType().representation().equals("string"));
    assert(secondMeta.symbolType().representation().equals("uint32"));
  }

  @Test
  public void testChainDerived() {
    StaticSpaceExpression codeFile = TestFileLoader.TYPE_RESOLUTION_TESTS;
    GlobalSymbolTable symbolTable = codeFile.globalSymbolTable();
    symbolTable.resolveSymbolNames();

    SymbolTableEntry first = symbolTable.getByFqn("chainDerived.firstChain").single();
    SymbolTableEntry second = symbolTable.getByFqn("chainDerived.secondChain").single();
    SymbolTableEntry third = symbolTable.getByFqn("chainDerived.thirdChain").single();
    SymbolTableEntry fourth = symbolTable.getByFqn("chainDerived.fourthChain").single();

    VariableTypeResolutionMetadata firstMeta = first.metadata().ofType(VariableTypeResolutionMetadata.class).single();
    VariableTypeResolutionMetadata secondMeta = second.metadata().ofType(VariableTypeResolutionMetadata.class).single();
    VariableTypeResolutionMetadata thirdMeta = third.metadata().ofType(VariableTypeResolutionMetadata.class).single();
    VariableTypeResolutionMetadata fourthMeta = fourth.metadata().ofType(VariableTypeResolutionMetadata.class).single();

    assert(firstMeta.symbolType().representation().equals("object"));
    assert(secondMeta.symbolType().representation().equals("object"));

    assert(thirdMeta.symbolType().representation().equals("uint64"));
    assert(fourthMeta.symbolType().representation().equals("uint64"));
  }
  @Test
  public void testContainsClosure() {
    StaticSpaceExpression codeFile = TestFileLoader.TYPE_RESOLUTION_TESTS;
    GlobalSymbolTable symbolTable = codeFile.globalSymbolTable();
    symbolTable.resolveSymbolNames();

    SymbolTableEntry parameter = symbolTable.getByFqn("containsClosure.closureFunction.closedOverParameter").single();
    SymbolTableEntry chainValue = symbolTable.getByFqn("containsClosure.closureFunction.closedOverChainValue").single();
    SymbolTableEntry number = symbolTable.getByFqn("containsClosure.closureFunction.closedOverNumber").single();

    VariableTypeResolutionMetadata parameterMeta = parameter.metadata()
      .ofType(VariableTypeResolutionMetadata.class).single();
    VariableTypeResolutionMetadata chainValueMeta = chainValue.metadata()
      .ofType(VariableTypeResolutionMetadata.class).single();
    VariableTypeResolutionMetadata numberMeta = number.metadata()
      .ofType(VariableTypeResolutionMetadata.class).single();

    assert(parameterMeta.symbolType().representation().equals("string"));
    assert(chainValueMeta.symbolType().representation().equals("string"));
    assert(numberMeta.symbolType().representation().equals("uint32"));
  }
}
