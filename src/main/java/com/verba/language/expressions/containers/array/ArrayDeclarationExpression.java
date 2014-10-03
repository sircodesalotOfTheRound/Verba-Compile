package com.verba.language.expressions.containers.array;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.verba.language.graph.visitors.SyntaxGraphVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.categories.DataContainerExpression;
import com.verba.language.expressions.categories.RValueExpression;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.info.LexInfo;
import com.verba.language.parsing.tokens.EnclosureToken;
import com.verba.language.parsing.tokens.NumericToken;
import com.verba.language.parsing.tokens.operators.OperatorToken;

/**
 * Created by sircodesalot on 14-2-24.
 */
public class ArrayDeclarationExpression extends VerbaExpression implements RValueExpression,
  DataContainerExpression {

  QList<VerbaExpression> items = new QList<>();

  public ArrayDeclarationExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    lexer.readCurrentAndAdvance(EnclosureToken.class, "[");
    while (lexer.notEOF() && !lexer.currentIs(EnclosureToken.class, "]")) {
      items.add(VerbaExpression.read(parent, lexer));
      if (lexer.currentIs(OperatorToken.class, ",")) lexer.readCurrentAndAdvance();
    }
    lexer.readCurrentAndAdvance(EnclosureToken.class, "]");
    this.closeLexingRegion();
  }

  private LexInfo readContents(Lexer lexer) {
    return lexer.readCurrentAndAdvance(NumericToken.class);
  }

  @Override
  public QIterable<VerbaExpression> items() {
    return this.items;
  }

  public static ArrayDeclarationExpression read(VerbaExpression parent, Lexer lexer) {
    return new ArrayDeclarationExpression(parent, lexer);
  }

  @Override
  public void accept(SyntaxGraphVisitor visitor) {
    visitor.visit(this);
  }
}

