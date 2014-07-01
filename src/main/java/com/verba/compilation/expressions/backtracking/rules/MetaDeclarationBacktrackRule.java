package main.java.verba.language.expressions.backtracking.rules;

import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.backtracking.BacktrackRule;
import main.java.verba.language.expressions.backtracking.MismatchException;
import main.java.verba.language.expressions.blockheader.classes.MetaDeclarationExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-2-23.
 */
public class MetaDeclarationBacktrackRule extends BacktrackRule {
    @Override
    public boolean attemptIf(VerbaExpression parent, Lexer lexer, LexList restOfLine) {
        return restOfLine.startsWith(KeywordToken.class, "meta");
    }

    @Override
    public VerbaExpression attempt(VerbaExpression parent, Lexer lexer, LexList restOfLine) throws MismatchException {
        if (restOfLine.startsWith(KeywordToken.class, "meta")) return MetaDeclarationExpression.read(parent, lexer);

        throw MismatchException.getInstance();
    }
}
