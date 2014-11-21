package com.verba.vblz.run;

import com.verba.vblz.run.arguments.ProgramLauncher;

import java.io.Serializable;

/**
 * Created by sircodesalot on 14-7-8.
 */
public class Program implements Serializable {

  public static void main(String[] args) throws Exception {
    ProgramLauncher launcher = new ProgramLauncher(args);
    launcher.run();
  }

}
