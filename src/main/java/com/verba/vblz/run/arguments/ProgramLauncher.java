package com.verba.vblz.run.arguments;

/**
 * Created by sircodesalot on 14/11/20.
 */
public class ProgramLauncher {
  private final ArgumentSet argset;

  public ProgramLauncher(String[] args) {
    this.argset = new ArgumentSet(args);
  }

  public int run() {
    return argset.run();
  }
}
