package com.verba.language.expressions.categories;

/**
 * Created by sircodesalot on 14-5-22.
 */
public interface AssignmentExpression {
    public boolean hasRValue();

    public com.verba.language.expressions.categories.RValueExpression rvalue();
}
