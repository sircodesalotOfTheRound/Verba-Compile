package com.verba.language.parsing.tokens.operators.assignment;

/**
 * Created by sircodesalot on 14-2-27.
 */
public abstract class CompositeAssignmentToken extends AssignmentToken {
  protected CompositeAssignmentToken(String representation) {
    super(representation);
  }
}
