fn main() {
  val item = "Something"
  val second = "Something else"

  # Call the second function
  second()

  println(second)
  println("Something in quotes")
  println(item)
}

fn second() {
  val variable = "Moar stuff"

  println(variable)
}