package com.verba.language.test.typesig;

import com.verba.language.expressions.StaticSpaceExpression;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.blockheader.functions.TaskDeclarationExpression;
import com.verba.language.expressions.blockheader.varname.NamedObjectDeclarationExpression;
import com.verba.language.test.tools.TestFileLoader;
import org.junit.Test;

/**
 * Created by sircodesalot on 14/9/9.
 */
public class TaskSignatureTesting {
    @Test
    public void testEmptyTask() {
        StaticSpaceExpression singleFileTest = TestFileLoader.TYPE_SIGNATURE_TESTS;

        TaskDeclarationExpression emptyTask = singleFileTest.allSubExpressions()
            .ofType(TaskDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("emptyTask"));

        assert(emptyTask != null);
        assert(!emptyTask.block().expressions().any());
        assert(!emptyTask.hasGenericParameters());
        assert(emptyTask.parameterSets().count() == 1);
        assert(!emptyTask.parameterSets().single().hasItems());
    }

    @Test
    public void testSingleDynamicArgumentTask() {
        StaticSpaceExpression singleFileTest = TestFileLoader.TYPE_SIGNATURE_TESTS;

        TaskDeclarationExpression singleDynamicArgumentTask = singleFileTest.allSubExpressions()
            .ofType(TaskDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("singleDynamicArgumentTask"));

        assert(singleDynamicArgumentTask != null);
        assert(!singleDynamicArgumentTask.block().expressions().any());
        assert(!singleDynamicArgumentTask.hasGenericParameters());
        assert(singleDynamicArgumentTask.parameterSets().count() == 1);
        assert(singleDynamicArgumentTask.parameterSets().single().items().singleOrNull() != null);

        VerbaExpression arg = singleDynamicArgumentTask.parameterSets().single().items().single();
        NamedObjectDeclarationExpression dynamicArgument = (NamedObjectDeclarationExpression)arg;

        assert(dynamicArgument.name().equals("dynamicArgument"));
        assert(!dynamicArgument.hasTypeConstraint());
    }

    @Test
    public void testSingleStaticTypedArgumentTask() {
        StaticSpaceExpression singleFileTest = TestFileLoader.TYPE_SIGNATURE_TESTS;

        TaskDeclarationExpression singleStaticTypedArgumentTask = singleFileTest.allSubExpressions()
            .ofType(TaskDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("singleStaticTypedArgumentTask"));

        assert(singleStaticTypedArgumentTask != null);
        assert(!singleStaticTypedArgumentTask.block().expressions().any());
        assert(!singleStaticTypedArgumentTask.hasGenericParameters());
        assert(singleStaticTypedArgumentTask.parameterSets().count() == 1);
        assert(singleStaticTypedArgumentTask.parameterSets().single().items().singleOrNull() != null);

        VerbaExpression arg = singleStaticTypedArgumentTask.parameterSets().single().items().single();
        NamedObjectDeclarationExpression dynamicArgument = (NamedObjectDeclarationExpression)arg;

        assert(dynamicArgument.name().equals("stringArgument"));
        assert(dynamicArgument.hasTypeConstraint());
        assert(dynamicArgument.typeDeclaration().representation().equals("string"));
    }


    @Test
    public void testMixedArgumentTask() {
        StaticSpaceExpression singleFileTest = TestFileLoader.TYPE_SIGNATURE_TESTS;

        TaskDeclarationExpression mixedArgumentTask = singleFileTest.allSubExpressions()
            .ofType(TaskDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("mixedArgumentTask"));

        assert(mixedArgumentTask != null);
        assert(!mixedArgumentTask.block().expressions().any());
        assert(!mixedArgumentTask.hasGenericParameters());
        assert(mixedArgumentTask.parameterSets().count() == 1);
        assert(mixedArgumentTask.parameterSets().single().items().count() == 3);

        NamedObjectDeclarationExpression stringArg = mixedArgumentTask
            .parameterSets()
            .single()
            .items()
            .firstAs(NamedObjectDeclarationExpression.class);

        assert(stringArg.name().equals("stringArg"));
        assert(stringArg.hasTypeConstraint());
        assert(stringArg.typeDeclaration().representation().equals("string"));

        NamedObjectDeclarationExpression intArg = (NamedObjectDeclarationExpression) mixedArgumentTask
            .parameterSets()
            .single()
            .items()
            .get(1);

        assert(intArg.name().equals("intArg"));
        assert(intArg.hasTypeConstraint());
        assert(intArg.typeDeclaration().representation().equals("uint64"));

        NamedObjectDeclarationExpression dynamicArg = mixedArgumentTask
            .parameterSets()
            .single()
            .items()
            .lastAs(NamedObjectDeclarationExpression.class);

        assert(dynamicArg.name().equals("dynamicArg"));
        assert(!dynamicArg.hasTypeConstraint());
    }

    @Test
    public void testExplicitReturnTypeTask() {
        StaticSpaceExpression singleFileTest = TestFileLoader.TYPE_SIGNATURE_TESTS;

        TaskDeclarationExpression explicitReturnTypeTask = singleFileTest.allSubExpressions()
            .ofType(TaskDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("explicitReturnTypeTask"));

        assert(explicitReturnTypeTask != null);
        assert(explicitReturnTypeTask.hasTypeConstraint());
        assert(explicitReturnTypeTask.typeDeclaration().representation().equals("uint64"));
        assert(!explicitReturnTypeTask.block().expressions().any());
        assert(!explicitReturnTypeTask.hasGenericParameters());
        assert(explicitReturnTypeTask.parameterSets().count() == 1);
        assert(!explicitReturnTypeTask.parameterSets().single().hasItems());
    }

    @Test
    public void testGenericTask() {
        StaticSpaceExpression singleFileTest = TestFileLoader.TYPE_SIGNATURE_TESTS;

        TaskDeclarationExpression genericTask = singleFileTest.allSubExpressions()
            .ofType(TaskDeclarationExpression.class)
            .singleOrNull(function -> function.name().equals("genericTask"));

        assert(genericTask != null);
        assert(genericTask.hasGenericParameters());
        assert(genericTask.genericParameters().first().representation().equals("T"));
        assert(genericTask.genericParameters().last().hasTypeConstraint());
        assert(genericTask.genericParameters().last().identifier().representation().equals("U"));
        assert(genericTask.genericParameters().last().typeDeclaration().representation().equals("object"));
        assert(!genericTask.block().expressions().any());
        assert(genericTask.parameterSets().count() == 1);
        assert(!genericTask.parameterSets().single().hasItems());
    }
}
