package main.java.verba.language.lexing.tokens;

import main.java.verba.language.lexing.codestream.CodeStream;
import main.java.verba.language.lexing.tokenization.Token;

/**
 * Created by sircodesalot on 14-2-19.
 */
public class QuoteToken implements Token {
    String quotation;

    public QuoteToken(CodeStream stream) {
        this.quotation = readQuote(stream);
    }

    private String readQuote(CodeStream stream) {
        StringBuilder builder = new StringBuilder();

        // Read the initial token
        builder.append(stream.read());

        while (stream.hasNext()) {
            Character next = stream.read();
            builder.append(next);
            if (next == '"') break;
        }

        return builder.toString();
    }

    public static QuoteToken read(CodeStream codeStream) {
        return new QuoteToken(codeStream);
    }

    @Override
    public String toString() {
        return String.format("%s", this.quotation);
    }
}
