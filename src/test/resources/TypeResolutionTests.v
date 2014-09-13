# Test function with explicit return type
fn explicitUnitFunction() : unit { }

# Test function with explicit parameters
fn explicitParameterFunction(first : string, second : uint32) { }

# Test function with explicitly typed variables
fn explicitVariables() {
  val first : string = "A String here"
  muta second : uint32 = 42
}

# Test function with a variable types derived from parameters
fn typesDerivedFromParameter(first : string, second : uint32) {
  val derivedFirst = first
  muta derivedSecond = second
}

# Test chain derivation
fn chainDerived(lhs : object, rhs : uint64) {
  val firstChain = lhs
  muta secondChain = firstChain

  muta thirdChain = rhs
  val fourthChain = thirdChain
}

# Test closed-over derivation
fn containsClosure(parameter : string) {
   val parameterChained = parameter
   val number = 10

   fn closureFunction() {
     val closedOverParameter = parameter
     val closedOverChainValue = parameterChained
     val closedOverNumber = number
   }
}
