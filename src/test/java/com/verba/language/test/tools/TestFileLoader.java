package com.verba.language.test.tools;

import com.verba.language.build.codepage.VerbaCodePage;
import com.verba.language.expressions.StaticSpaceExpression;

/**
 * Created by sircodesalot on 14/9/9.
 */
public class TestFileLoader {
  public static final StaticSpaceExpression TYPE_SIGNATURE_TESTS =
    new StaticSpaceExpression(VerbaCodePage.fromResourceStream("/TypeSignatureTests.v"));
}
