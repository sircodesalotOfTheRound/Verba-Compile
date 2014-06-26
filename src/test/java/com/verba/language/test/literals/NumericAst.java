package com.verba.language.test.literals;

import com.javalinq.interfaces.QIterable;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.rvalue.simple.NumericExpression;
import com.verba.language.test.tools.AstTools;
import org.junit.Test;

/**
 * Created by sircodesalot on 14-4-25.
 */
public class NumericAst {
    @Test
    public void checkNumericAst() {
        QIterable<VerbaExpression> expressions
            = AstTools.generateAstCollectionFromString("12345");

        assert (expressions.count() == 1);
        assert (expressions.all(exp -> exp instanceof NumericExpression));
    }
}
