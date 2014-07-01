package main.java.verba.language.validation.parameters;

import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.blockheader.generic.GenericTypeListExpression;
import main.java.verba.language.validation.ExpressionValidator;
import main.java.verba.language.validation.violations.ValidationViolation;
import main.java.verba.language.validation.violations.ValidationViolationList;

/**
 * Created by sircodesalot on 14-5-5.
 */
public class GenericParameterValidator extends ExpressionValidator<GenericTypeListExpression> {
    public GenericParameterValidator(GenericTypeListExpression genericParameters) {
        super(genericParameters);
    }

    public ValidationViolationList verifyAllParametersExplicitlyTyped() {
        QIterable<ValidationViolation> violations = this.genericParameters()
            .where(parameter -> !parameter.hasTypeConstraint())
            .map(parameter -> {
                return new ValidationViolation(parameter, "Generic parameter %s must have type constraint",
                    parameter.representation());
            });

        return new ValidationViolationList(violations);
    }

    public GenericTypeListExpression genericParameters() {
        return super.target();
    }
}
