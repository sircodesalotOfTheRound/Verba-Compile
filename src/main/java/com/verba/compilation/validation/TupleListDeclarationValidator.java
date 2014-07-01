package main.java.verba.language.validation;

/**
 * Created by sircodesalot on 14-5-22.
 */


import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.containers.tuple.TupleDeclarationExpression;

import java.util.function.Predicate;

/**
 * Created by sircodesalot on 14-5-5.
 */
public class TupleListDeclarationValidator extends ExpressionValidator<QIterable<TupleDeclarationExpression>> {
    public TupleListDeclarationValidator(QIterable<TupleDeclarationExpression> tuple) {
        super(tuple);
    }

    public QIterable<TupleDeclarationExpression> tupleList() {
        return super.target();
    }

    public QIterable<VerbaExpression> findArguments(Predicate<VerbaExpression> predicate) {
        return this.tupleList()
            .flatten(tupleList -> tupleList.items())
            .where(argument -> predicate.test(argument));
    }
}
