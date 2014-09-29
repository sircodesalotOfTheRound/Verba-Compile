fn main() {
  fn another() {
     print("this is from another or something")
  }

  println("this is from main")

  # Need to fix fully qualified names.
  another()
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