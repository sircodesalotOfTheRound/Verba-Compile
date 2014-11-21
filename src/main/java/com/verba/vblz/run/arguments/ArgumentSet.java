package com.verba.vblz.run.arguments;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.javalinq.tools.Partition;
import com.verba.vblz.run.arguments.launchers.BuildLauncher;
import com.verba.vblz.run.arguments.launchers.VersionLauncher;

/**
 * Created by sircodesalot on 14/11/20.
 */
public class ArgumentSet {
  private static QIterable<ArgumentRunner> runners =
    new QList<ArgumentRunner>(
      new BuildLauncher(),
      new VersionLauncher());

  private static Partition<String, ArgumentRunner> argsMap =
    runners.parition(ArgumentRunner::name);

  private QList<String> arguments = new QList<>();
  private String command = null;

  public ArgumentSet(String[] args) {
    addArguments(args);
  }

  public void displayHelp() {
    QIterable<String> helpMessages = ArgumentSet.runners
      .map(runner -> String.format("    %-30s - %s", runner.name(), runner.description()));

    System.out.println("vblz: ");
    for (String message : helpMessages) {
      System.out.println(message);
    }
  }

  public void addArguments(String[] args) {
    // Set the arguments.
    for (String arg : args) {
      this.arguments.add(arg);
    }

    // Set the command.
    if (this.arguments.any()) {
      this.command = this.arguments.first();
    }
  }

  private boolean shouldShowHelp() {
    return ((this.arguments.count() < 1))
      || (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("-h"))
      || !argsMap.containsKey(command);
  }

  public int run() {
    if (argsMap.containsKey(command)) {
      ArgumentRunner commandRunner = argsMap.get(command).single();
      return commandRunner.run(this);
    } else {
      displayHelp();
    }
    return 0;
  }
}
