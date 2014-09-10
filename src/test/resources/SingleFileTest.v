# SingleFileTest.v -- Simple tests for making sure basic language stuff works

# Function signature testing:
fn emptyFunction() { }
fn singleDynamicArgumentFunction(dynamicArgument) {  }
fn singleStaticTypedArgumentFunction(stringArgument : string) {  }
fn mixedArgumentFunction(stringArg : string, intArg : uint64, dynamicArg) { }

# Inline class tests:
class EmptyInlineClass()
class ParameterlessInlineClass
class DynamicParameterInlineClass(dynamicParameter)
class StaticParameterInlineClass(str : string)