package com.verba.vblz.run.arguments.launchers;

import com.verba.vblz.run.arguments.ArgumentRunner;
import com.verba.vblz.run.arguments.ArgumentSet;

/**
 * Created by sircodesalot on 14/11/20.
 */
public class BuildLauncher extends ArgumentRunner {
  public BuildLauncher() {
    super("build-runner", "performs a build run");
  }

  @Override
  public int run(ArgumentSet argumentSet) {
    System.out.println("run!");
    return 0;
  }

  @Override
  public void displayHelp() {
    System.out.println("performs a quick run");
  }
}
