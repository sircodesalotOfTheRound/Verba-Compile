package com.verba.vblz.run.arguments;

/**
 * Created by sircodesalot on 14/11/20.
 */
public abstract class ArgumentRunner {
  private final String name;
  private final String description;

  public ArgumentRunner(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String name() { return name; }
  public String description() { return description; }

  public abstract int run(ArgumentSet argumentSet);
  public abstract void displayHelp();
}
