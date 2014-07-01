package main.java.verba.language.ast;

import main.java.verba.language.expressions.VerbaExpression;

/**
 * AST Node callbacks are used by AST visitors to perform select code when a node is reached.
 * I.E, as the visitor descends the tree, the callback is called for each node found.
 */
public interface VerbaASTNodeCallback {
    public void callback(VerbaExpression node);
}
