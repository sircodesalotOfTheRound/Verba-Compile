package main.java.verba.language.validation.parameters;

import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.containers.tuple.TupleDeclarationExpression;
import main.java.verba.language.validation.ExpressionValidator;

import java.util.function.Predicate;

/**
 * Created by sircodesalot on 14-5-5.
 */
public class TupleDeclarationValidator extends ExpressionValidator<TupleDeclarationExpression> {
    public TupleDeclarationValidator(TupleDeclarationExpression tuple) {
        super(tuple);
    }

    public TupleDeclarationExpression tuple() {
        return super.target();
    }

    public QIterable<VerbaExpression> findViolatingArguments(Predicate<VerbaExpression> predicate) {
        return this.tuple().items().where(argument -> !predicate.test(argument));
    }
}
