package main.java.verba.language.lexing.tokenization;

import main.java.verba.language.lexing.LexicalTokenizerBase;
import main.java.verba.language.lexing.codestream.CodeStream;
import main.java.verba.language.lexing.info.LexInfo;
import main.java.verba.language.lexing.info.TokenPosition;
import main.java.verba.language.lexing.tokens.EnclosureToken;
import main.java.verba.language.lexing.tokens.NumericToken;
import main.java.verba.language.lexing.tokens.QuoteToken;
import main.java.verba.language.lexing.tokens.identifiers.IdentifierToken;
import main.java.verba.language.lexing.tokens.ignorable.LineCommentToken;
import main.java.verba.language.lexing.tokens.ignorable.UnknownToken;
import main.java.verba.language.lexing.tokens.ignorable.WhitespaceToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-4-13.
 */
public class VerbaLexicalTokenizer extends LexicalTokenizerBase {
    private final String filename;
    private final char DOUBLE_QUOTE = '"';

    public VerbaLexicalTokenizer(String filename, CodeStream codeStream) {
        super(codeStream);

        this.filename = filename;
    }

    public LexInfo readNext() {
        TokenPosition position = new TokenPosition(this.codeStream);

        // Process the readNext item
        Character next = codeStream.peek();

        if (WhitespaceToken.isWhitespaceToken(next))
            return new LexInfo(WhitespaceToken.read(codeStream), position);

        else if (next == DOUBLE_QUOTE)
            return new LexInfo(QuoteToken.read(this.codeStream), position);

        else if (LineCommentToken.isLineCommentToken(this.codeStream))
            return new LexInfo(LineCommentToken.read(codeStream), position);

        else if (Character.isLetter(next) || next == '_')
            return new LexInfo(IdentifierToken.read(this.codeStream), position);

        else if (EnclosureToken.isEnclosureToken(next))
            return new LexInfo(EnclosureToken.read(this.codeStream), position);

        else if (Character.isDigit(next))
            return new LexInfo(NumericToken.read(this.codeStream), position);

        else if (OperatorToken.isOperatorToken(next))
            return new LexInfo(OperatorToken.read(this.codeStream), position);

            // If we can't tell what kind of token this is, then return nothing.
        else
            return new LexInfo(UnknownToken.read(this.codeStream), position);
    }

}
