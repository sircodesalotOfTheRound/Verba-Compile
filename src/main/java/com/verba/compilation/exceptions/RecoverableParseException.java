package main.java.verba.language.exceptions;

/**
 * Created by sircodesalot on 14-2-16.
 */
public class RecoverableParseException extends CompilerException {
    public RecoverableParseException(String message, Object... args) {
        super(message, args);
    }
}
