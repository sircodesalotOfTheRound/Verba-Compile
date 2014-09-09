# SingleFileTest.v -- Simple tests for making sure basic language stuff works

# Simple signature testing (dynamic and statically typed functions)
fn emptyFunction() { }
fn singleDynamicArgumentFunction(dynamicArgument) {  }
fn singleStaticTypedArgumentFunction(stringArgument : string) {  }
fn mixedArgumentFunction(stringArg : string, intArg : uint64, dynamicArg) { }

# Simple class testing
class EmptyInlineClass()