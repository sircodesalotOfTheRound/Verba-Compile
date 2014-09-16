package com.verba.language.test.statements;

import com.javalinq.implementations.QList;
import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.rvalue.math.MathExpression;
import com.verba.language.expressions.rvalue.math.RpnMap;
import com.verba.language.expressions.rvalue.simple.MathOpExpression;
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
      RpnMap expressions = mathExpression.expressions();

      QList<VerbaExpression> polishNotation = expressions.getPolishNotation();
      assert(polishNotation.firstAs(NumericExpression.class).asInt() == 5);
      assert(polishNotation.getAs(1, NumericExpression.class).asInt() == 7);
      assert(polishNotation.lastAs(MathOpExpression.class).operator().representation().equals("+"));
    }

}
