package main.java.verba.language.expressions.members;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import main.java.verba.language.expressions.VerbaExpression;
import main.java.verba.language.expressions.categories.TypeDeclarationExpression;
import main.java.verba.language.lexing.Lexer;
import main.java.verba.language.lexing.tokens.identifiers.IdentifierToken;
import main.java.verba.language.lexing.tokens.operators.OperatorToken;

import java.util.Iterator;

/**
 * Created by sircodesalot on 14-2-24.
 */
public class FullyQualifiedNameExpression extends VerbaExpression
    implements TypeDeclarationExpression, QIterable<MemberExpression> {

    QList<MemberExpression> fullyQualifiedName = new QList<MemberExpression>();

    private FullyQualifiedNameExpression(VerbaExpression parent, Lexer lexer) {
        super(parent, lexer);

        fullyQualifiedName.add(MemberExpression.read(this, lexer));

        while (lexer.notEOF() && lexer.currentIs(OperatorToken.class, ".")) {
            lexer.readCurrentAndAdvance(OperatorToken.class, ".");
            fullyQualifiedName.add(MemberExpression.read(this, lexer));
        }

    }

    public static boolean IsFullyQualifiedName(Lexer lexer) {
        return lexer.currentIs(IdentifierToken.class);
    }

    public static FullyQualifiedNameExpression read(VerbaExpression parent, Lexer lexer) {
        return new FullyQualifiedNameExpression(parent, lexer);
    }

    public QIterable<MemberExpression> members() {
        return this.fullyQualifiedName;
    }

    public String representation() {
        Iterable<String> fqnItems = this.fullyQualifiedName.map(item -> item.representation());
        return String.join(".", fqnItems);
    }

    @Override
    public Iterator<MemberExpression> iterator() {
        return this.fullyQualifiedName.iterator();
    }
}
