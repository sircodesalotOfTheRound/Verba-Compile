package com.verba.language.test.statements;

import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.rvalue.math.BinaryMathExpression;
import com.verba.language.expressions.rvalue.math.MathExpression;
import com.verba.language.expressions.rvalue.simple.NumericExpression;
import com.verba.language.expressions.statements.declaration.ValDeclarationStatement;
import com.verba.language.symbols.table.entries.SymbolTableEntry;
import com.verba.language.test.loader.TestFileLoader;
import org.junit.Test;

/**
 * Created by sircodesalot on 14/9/15.
 */
public class StatementTests {
    @Test
  public void testGenericFunction() {
    StaticSpaceExpression statementTests = TestFileLoader.STATEMENT_TESTS;

      SymbolTableEntry mathStatements = statementTests.globalSymbolTable().getByFqn("math_statements.value").single();
      ValDeclarationStatement statement = mathStatements.instanceAs(ValDeclarationStatement.class);

      MathExpression mathExpression = (MathExpression) statement.rvalue();
      BinaryMathExpression binary = mathExpression.expressions().single();

      assert(((NumericExpression)binary.lhs()).asInt() == 5);
      assert(binary.operation().representation().equals("+"));
      assert(((NumericExpression)binary.rhs()).asInt() == 7);
    }

}
