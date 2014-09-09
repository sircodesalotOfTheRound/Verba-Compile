package com.verba.language.test.general;

import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.test.tools.TestFileLoader;
import org.junit.Test;

/**
 * Created by sircodesalot on 14/9/9.
 */
public class FunctionSignatureTesting {
    @Test
    public void testEmptyFunction() {
        StaticSpaceExpression singleFileTest = TestFileLoader.SINGLE_FILE_TEST;

        FunctionDeclarationExpression emptyFunction = singleFileTest.allSubExpressions().expressions()
            .ofType(FunctionDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("emptyFunction"));

        assert(!emptyFunction.block().expressions().any());
        assert(!emptyFunction.hasGenericParameters());
        assert(emptyFunction.parameterSets().count() == 1);
        assert(!emptyFunction.parameterSets().single().hasItems());
    }
}
