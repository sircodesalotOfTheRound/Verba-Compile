package com.verba.language.expressions.categories;

import com.verba.language.ast.FunctionElementVisitor;
import com.verba.language.codegen.generators.FunctionGraph;
import com.verba.language.graphs.function.VariableLifetimeGraph;

/**
 * Created by sircodesalot on 14-5-14.
 */
public interface FunctionElementExpression {
  void accept(FunctionElementVisitor visitor);
}
