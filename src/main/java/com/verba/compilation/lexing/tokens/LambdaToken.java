package main.java.verba.language.lexing.tokens;

import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.lexing.codestream.CodeStream;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-26.
 */
public class LambdaToken extends OperatorToken {
    public LambdaToken() {
        super(' ');
    }

    public static boolean isLambdaToken(Character firstToken, CodeStream stream) {
        return (firstToken == '-' && stream.peek() == '>');
    }

    public static LambdaToken read(Character firstToken, CodeStream stream) {
        if (firstToken == '-' && stream.peek() == '>') {
            stream.read();
            return new LambdaToken();
        } else throw new ParseException("Expected lambda token");
    }

    @Override
    public String toString() {
        return "->";
    }
}
