package com.verba.language.expressions.access;

import com.verba.language.exceptions.CompilerException;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.test.lexing.info.LexInfo;
import com.verba.language.test.lexing.tokens.identifiers.KeywordToken;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Created by sircodesalot on 14-5-20.
 */
public class AccessModifierExpression extends VerbaExpression {
    public static final Set<String> ACCESS_MODIFIERS = new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            Set<String> modifiers = new HashSet<>();
            modifiers.add("public");
            modifiers.add("protected");
            modifiers.add("private");
            modifiers.add("internal");
            modifiers.add("abstract");

            return modifiers;
        }
    }.get();

    private final LexInfo accessModifier;

    public AccessModifierExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        // Validate that it was in fact a
        if (!ACCESS_MODIFIERS.contains(lexer.current().representation()))
            throw new CompilerException("Invalid access modifier");

        this.accessModifier = lexer.readCurrentAndAdvance(KeywordToken.class);

    }

    public static AccessModifierExpression read(VerbaExpression expression, Lexer lexer) {
        return new AccessModifierExpression(expression, lexer);
    }

    public LexInfo accessModifier() {
        return this.accessModifier;
    }
}
