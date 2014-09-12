package com.verba.language.ast.visitor;

/**
 * Created by sircodesalot on 14/9/12.
 */
public interface AstVisitable {
  void accept(AstVisitor visitor);
}
