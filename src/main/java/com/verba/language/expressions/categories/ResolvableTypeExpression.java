package com.verba.language.expressions.categories;

import com.verba.language.build.resolution.SymbolResolver;

/**
 * Created by sircodesalot on 14/9/12.
 */
public interface ResolvableTypeExpression {
  void accept(SymbolResolver resolver);
}
