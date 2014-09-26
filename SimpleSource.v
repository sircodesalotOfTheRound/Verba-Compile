fn main() {
  println("this is from main")
  second()
}

fn second() {
  println("this is from second")

  third()
}

fn third() {
  println("this is from third")
  fourth()
}

fn fourth() {
  println("this is from fourth")

  stackOverflow()
}

fn stackOverflow() {
  println("stack overflow!!!")
  main()
}