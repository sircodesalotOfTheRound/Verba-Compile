package com.verba.vblz.run.argparse;

import com.verba.vblz.newproject.CreateNewProjectTask;
import com.verba.vblz.newproject.buildscript.CreateBuildScriptTask;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class ArgumentParser {
    private final String[] args;


    public ArgumentParser(String[] args) {
        this.args = args;
    }

    public void runApplication() {
        if (contains("new")) {
            CreateNewProjectTask.run(args);
        }
    }

    private boolean contains(String arg) {
        for (String argument : args) {
            if (argument != null && argument.equalsIgnoreCase(arg)) {
                return true;
            }
        }

        return false;
    }
}
