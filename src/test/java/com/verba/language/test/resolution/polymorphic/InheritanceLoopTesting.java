package com.verba.language.test.resolution.polymorphic;

import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.classes.ClassDeclarationExpression;
import com.verba.language.test.lexing.Lexer;
import com.verba.language.symbols.resolution.polymorphic.PolymorphicResolutionMetadata;
import com.verba.language.symbols.resolution.polymorphic.PolymorphicResolver;
import com.verba.language.test.tools.GeneralLexing;
import org.junit.Test;

/**
 * Should catch that there is an inheritance loop and fail gracefully.
 */
public class InheritanceLoopTesting {
    @Test
    public void testInheritanceLoops() throws Exception {
        Lexer lexer = GeneralLexing.generateFromResourceFile("/Resolution/Polymorphic/InheritanceLoopTesting.v");
        ClassDeclarationExpression lowerClass = (ClassDeclarationExpression) VerbaExpression.read(null, lexer);
        ClassDeclarationExpression middleClass = (ClassDeclarationExpression) VerbaExpression.read(null, lexer);
        ClassDeclarationExpression upperClass = (ClassDeclarationExpression) VerbaExpression.read(null, lexer);

        StaticSpaceExpression staticSpaceExpression = new StaticSpaceExpression(lowerClass, middleClass, upperClass);

        PolymorphicResolver resolver = new PolymorphicResolver(staticSpaceExpression.globalSymbolTable());
        PolymorphicResolutionMetadata classMemberResolutionMetadata = resolver.resolve(upperClass);

        assert (classMemberResolutionMetadata.containsInheritanceLoop());
    }
}
