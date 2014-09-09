package com.verba.language.test.functions;

import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.functions.FunctionDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedObjectDeclarationExpression;
import com.verba.language.test.tools.TestFileLoader;
import org.junit.Test;

/**
 * Created by sircodesalot on 14/9/9.
 */
public class FunctionSignatureTesting {
    @Test
    public void testEmptyFunction() {
        StaticSpaceExpression singleFileTest = TestFileLoader.SINGLE_FILE_TEST;

        FunctionDeclarationExpression emptyFunction = singleFileTest.allSubExpressions()
            .ofType(FunctionDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("emptyFunction"));

        assert(emptyFunction != null);
        assert(!emptyFunction.block().expressions().any());
        assert(!emptyFunction.hasGenericParameters());
        assert(emptyFunction.parameterSets().count() == 1);
        assert(!emptyFunction.parameterSets().single().hasItems());
    }

    @Test
    public void testSingleDynamicArgumentFunction() {
        StaticSpaceExpression singleFileTest = TestFileLoader.SINGLE_FILE_TEST;

        FunctionDeclarationExpression singleDynamicArgumentFunction = singleFileTest.allSubExpressions()
            .ofType(FunctionDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("singleDynamicArgumentFunction"));

        assert(singleDynamicArgumentFunction != null);
        assert(!singleDynamicArgumentFunction.block().expressions().any());
        assert(!singleDynamicArgumentFunction.hasGenericParameters());
        assert(singleDynamicArgumentFunction.parameterSets().count() == 1);
        assert(singleDynamicArgumentFunction.parameterSets().single().items().singleOrNull() != null);

        VerbaExpression arg = singleDynamicArgumentFunction.parameterSets().single().items().single();
        NamedObjectDeclarationExpression dynamicArgument = (NamedObjectDeclarationExpression)arg;

        assert(dynamicArgument.name().equals("dynamicArgument"));
        assert(!dynamicArgument.hasTypeConstraint());
    }

    @Test
    public void testSingleStaticTypedArgumentFunction() {
        StaticSpaceExpression singleFileTest = TestFileLoader.SINGLE_FILE_TEST;

        FunctionDeclarationExpression singleStaticTypedArgumentFunction = singleFileTest.allSubExpressions()
            .ofType(FunctionDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("singleStaticTypedArgumentFunction"));

        assert(singleStaticTypedArgumentFunction != null);
        assert(!singleStaticTypedArgumentFunction.block().expressions().any());
        assert(!singleStaticTypedArgumentFunction.hasGenericParameters());
        assert(singleStaticTypedArgumentFunction.parameterSets().count() == 1);
        assert(singleStaticTypedArgumentFunction.parameterSets().single().items().singleOrNull() != null);

        VerbaExpression arg = singleStaticTypedArgumentFunction.parameterSets().single().items().single();
        NamedObjectDeclarationExpression dynamicArgument = (NamedObjectDeclarationExpression)arg;

        assert(dynamicArgument.name().equals("stringArgument"));
        assert(dynamicArgument.hasTypeConstraint());
        assert(dynamicArgument.typeDeclaration().representation().equals("string"));
    }


    @Test
    public void testMixedArgumentFunction() {
        StaticSpaceExpression singleFileTest = TestFileLoader.SINGLE_FILE_TEST;

        FunctionDeclarationExpression mixedArgumentFunction = singleFileTest.allSubExpressions()
            .ofType(FunctionDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("mixedArgumentFunction"));

        assert(mixedArgumentFunction != null);
        assert(!mixedArgumentFunction.block().expressions().any());
        assert(!mixedArgumentFunction.hasGenericParameters());
        assert(mixedArgumentFunction.parameterSets().count() == 1);
        assert(mixedArgumentFunction.parameterSets().single().items().count() == 3);

        NamedObjectDeclarationExpression stringArg = mixedArgumentFunction
            .parameterSets()
            .single()
            .items()
            .firstAs(NamedObjectDeclarationExpression.class);

        assert(stringArg.name().equals("stringArg"));
        assert(stringArg.hasTypeConstraint());
        assert(stringArg.typeDeclaration().representation().equals("string"));

        NamedObjectDeclarationExpression intArg = (NamedObjectDeclarationExpression) mixedArgumentFunction
            .parameterSets()
            .single()
            .items()
            .get(1);

        assert(intArg.name().equals("intArg"));
        assert(intArg.hasTypeConstraint());
        assert(intArg.typeDeclaration().representation().equals("uint64"));

        NamedObjectDeclarationExpression dynamicArg = mixedArgumentFunction
            .parameterSets()
            .single()
            .items()
            .lastAs(NamedObjectDeclarationExpression.class);

        assert(dynamicArg.name().equals("dynamicArg"));
        assert(!dynamicArg.hasTypeConstraint());
    }

}
