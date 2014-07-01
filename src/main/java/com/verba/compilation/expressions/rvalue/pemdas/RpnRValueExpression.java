package main.java.verba.language.expressions.rvalue.pemdas;

import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.RValueExpression;
import main.java.verba.language.lexing.Lexer;

/**
 * Created by sircodesalot on 14-2-27.
 */
public class RpnRValueExpression extends VerbaExpression implements RValueExpression {
    private final RpnMap rpnmap;

    private RpnRValueExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        this.rpnmap = new RpnMap(this, lexer);

        try {
            this.rpnmap.project();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static RpnRValueExpression read(VerbaExpression parent, Lexer lexer) {
        return new RpnRValueExpression(parent, lexer);
    }

    public QIterable<VerbaExpression> rpnStack() {
        return this.rpnmap.getPolishNotation();
    }
}
