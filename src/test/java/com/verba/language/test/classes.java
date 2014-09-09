package com.verba.language.test;

import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.blockheader.classes.ClassDeclarationExpression;
import com.verba.language.test.tools.TestFileLoader;
import org.junit.Test;
import sun.tools.java.ClassDeclaration;

/**
 * Created by sircodesalot on 14/9/9.
 */
public class classes {
    @Test
    public void simpleInlineClassTest() {
        StaticSpaceExpression singleFileTest = TestFileLoader.SINGLE_FILE_TEST;
        ClassDeclarationExpression expression = singleFileTest.allSubExpressions()
            .ofType(ClassDeclarationExpression.class)
            .single(declaration -> declaration.name().equals("EmptyInlineClass"));

        assert (expression.isInlineClass());
        assert (!expression.hasBlock());
        assert (!expression.hasTraits());
        assert (!expression.hasGenericParameters());
    }
}
