package main.java.verba.language.symbols.resolution.fqn;

import main.java.verba.language.expressions.members.MemberExpression;
import main.java.verba.language.symbols.resolution.interfaces.SymbolResolver;

/**
 * Created by sircodesalot on 14-5-16.
 */
public class MemberResolver implements SymbolResolver<MemberExpression, MemberResolutionInfo> {
    @Override
    public MemberResolutionInfo resolve(MemberExpression expression) {
        return null;
    }
}
