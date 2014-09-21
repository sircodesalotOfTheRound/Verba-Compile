package com.verba.language.expressions.categories;

import com.verba.language.codegen.generators.FunctionImageSegmentGenerator;

/**
 * Created by sircodesalot on 14-5-14.
 */
public interface FunctionElementExpression {
  void accept(FunctionImageSegmentGenerator functionImageGenerator);
}
