package com.verba.vblz.newproject;

import com.verba.vblz.helpers.EnvironmentHelpers;
import com.verba.vblz.newproject.buildscript.CreateBuildScriptTask;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class CreateNewProjectTask {

    private final CreateBuildScriptTask buildScriptTask;

    private CreateNewProjectTask(String[] args) {
        buildScriptTask = new CreateBuildScriptTask(
            EnvironmentHelpers.getHostname(),
            EnvironmentHelpers.getCurrentFolder(),
            "1.0");
    }

    public void writeFile() throws Exception {
        this.buildScriptTask.perform();
    }

    public static void run(String[] args) {
        try {
            CreateNewProjectTask task = new CreateNewProjectTask(args);
            task.writeFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
