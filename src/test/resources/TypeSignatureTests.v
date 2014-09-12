# SingleFileTest.v -- Simple tests for making sure basic language stuff works

# Function signature testing:
fn emptyFunction() { }
fn singleDynamicArgumentFunction(dynamicArgument) {  }
fn singleStaticTypedArgumentFunction(stringArgument : string) {  }
fn mixedArgumentFunction(stringArg : string, intArg : uint64, dynamicArg) { }
fn explicitReturnTypeFunction() : uint64 { }
fn genericFunction<T, U : string>() { }

# Task signature testing:
task emptyTask() { }
task singleDynamicArgumentTask(dynamicArgument) {  }
task singleStaticTypedArgumentTask(stringArgument : string) {  }
task mixedArgumentTask(stringArg : string, intArg : uint64, dynamicArg) { }
task explicitReturnTypeTask() : uint64 { }
task genericTask<T, U : object>() { }

# Inline class tests:
class EmptyInlineClass()
class ParameterlessInlineClass
class ParameterlessDerivedClass : ParameterlessInlineClass
class DynamicParameterInlineClass(dynamicParameter)
class StaticParameterInlineClass(str : string)

# Inline trait tests:
trait EmptyInlineTrait()
trait ParameterlessInlineTrait
trait ParameterlessDerivedTrait : ParameterlessInlineTrait
trait DynamicParameterInlineTrait(dynamicParameter)
trait StaticParameterInlineTrait(str : string)

# Plain Signature Tests
# TODO: Unbreak this! (Symbol table needs rework).
# signature emptyFunction() : String
