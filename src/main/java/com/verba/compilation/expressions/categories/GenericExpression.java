package main.java.verba.language.expressions.categories;

import main.java.verba.language.expressions.blockheader.generic.GenericTypeListExpression;

/**
 * Created by sircodesalot on 14-5-30.
 */
public interface GenericExpression {
    boolean hasGenericParameters();

    GenericTypeListExpression genericParameters();
}
