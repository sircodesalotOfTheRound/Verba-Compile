package main.java.verba.language.validation.fqn;

import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.blockheader.varname.VarNameDeclarationExpression;
import main.java.verba.language.expressions.members.FullyQualifiedNameExpression;
import main.java.verba.language.expressions.members.MemberExpression;
import main.java.verba.language.validation.ExpressionValidator;

/**
 * Created by sircodesalot on 14-5-5.
 */
public class FullyQualifiedNameValidator extends ExpressionValidator<FullyQualifiedNameExpression> {
    public FullyQualifiedNameValidator(FullyQualifiedNameExpression fqn) {
        super(fqn);
    }

    public boolean hasParameters() {
        return this.fqn().any(MemberExpression::hasParameters);
    }

    public boolean hasGenericParameters() {
        return this.fqn().any(MemberExpression::hasGenericParameters);
    }

    public boolean hasSingleMember() {
        return this.fqn().count() == 1;
    }

    public QIterable<MemberExpression> membersWithGenericParameters() {
        return this.fqn().members().where(MemberExpression::hasGenericParameters);
    }

    public QIterable<MemberExpression> membersWithParameters() {
        return this.fqn().members().where(MemberExpression::hasParameters);
    }

    public QIterable<VerbaExpression> flattenedParameterList() {
        // Flatten the parameter lists, then flatten all parameters
        return this.membersWithParameters()
            .flatten(MemberExpression::parameterLists)
            .flatten(tuple -> tuple.items());
    }

    public QIterable<VarNameDeclarationExpression> flattenedGenericParameterList() {
        return this.membersWithGenericParameters()
            .flatten(MemberExpression::genericParameterList);
    }

    public FullyQualifiedNameExpression fqn() {
        return super.target();
    }
}
