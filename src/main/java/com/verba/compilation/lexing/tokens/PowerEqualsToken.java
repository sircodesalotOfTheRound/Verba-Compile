package main.java.verba.language.lexing.tokens;

import main.java.verba.language.lexing.tokens.operators.assignment.CompositeAssignmentToken;

/**
 * Created by sircodesalot on 14-2-26.
 */
public class PowerEqualsToken extends CompositeAssignmentToken {
    public PowerEqualsToken() {
        super("^=");
    }
}
