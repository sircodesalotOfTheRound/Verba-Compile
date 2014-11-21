package com.verba.vblz.run.arguments.launchers;

import com.verba.vblz.run.arguments.ArgumentRunner;
import com.verba.vblz.run.arguments.ArgumentSet;

/**
 * Created by sircodesalot on 14/11/20.
 */
public class VersionLauncher extends ArgumentRunner {
  public VersionLauncher() {
    super("version", "displays the product version");
  }

  @Override
  public int run(ArgumentSet argumentSet) {
    System.out.println("Verbalize version 1.0.0");
    return 0;
  }

  @Override
  public void displayHelp() {
    System.out.println("displays the product version");
  }
}
