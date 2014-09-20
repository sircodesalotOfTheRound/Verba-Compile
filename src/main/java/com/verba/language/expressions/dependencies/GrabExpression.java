package com.verba.language.expressions.dependencies;

import com.verba.language.ast.visitor.AstVisitor;
import com.verba.language.expressions.VerbaExpression;
import com.verba.language.expressions.members.FullyQualifiedNameExpression;
import com.verba.language.expressions.rvalue.simple.QuoteExpression;
import com.verba.language.parsing.Lexer;
import com.verba.language.parsing.tokens.QuoteToken;
import com.verba.language.parsing.tokens.identifiers.KeywordToken;

/**
 * Created by sircodesalot on 14-5-20.
 */
@Deprecated
public class GrabExpression extends VerbaExpression {
  private final VerbaExpression resourceName;

  public GrabExpression(VerbaExpression parent, Lexer lexer) {
    super(parent, lexer);

    lexer.readCurrentAndAdvance(KeywordToken.class, "grab");
    this.resourceName = this.readResourceName(lexer);
  }

  private VerbaExpression readResourceName(Lexer lexer) {
    if (lexer.currentIs(QuoteToken.class)) {
      return QuoteExpression.read(this, lexer);
    }

    return FullyQualifiedNameExpression.read(this, lexer);
  }

  public String resourceNameAsString() {
    if (resourceName instanceof FullyQualifiedNameExpression)
      return ((FullyQualifiedNameExpression) this.resourceName).representation();

    else return ((QuoteExpression) this.resourceName).representation();
  }


  public static GrabExpression read(VerbaExpression parent, Lexer lexer) {
    return new GrabExpression(parent, lexer);
  }

  @Override
  public void accept(AstVisitor visitor) {

  }
}
