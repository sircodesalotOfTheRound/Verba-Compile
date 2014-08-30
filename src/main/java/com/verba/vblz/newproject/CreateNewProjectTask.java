package com.verba.vblz.newproject;

import com.verba.vblz.helpers.EnvironmentHelpers;
import com.verba.vblz.helpers.display.ConsoleOutput;
import com.verba.vblz.helpers.tasks.Task;
import com.verba.vblz.helpers.tasks.TaskList;
import com.verba.vblz.newproject.subtasks.CreateBuildEnvironmentTask;
import com.verba.vblz.newproject.subtasks.CreateBuildScriptTask;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class CreateNewProjectTask implements Task{

    TaskList taskList = new TaskList();

    private CreateNewProjectTask(String[] args) {
        Task buildScriptTask = new CreateBuildScriptTask(
            EnvironmentHelpers.getHostname(),
            EnvironmentHelpers.getCurrentFolder(),
            "1.0");

        Task directoryCreationTask = new CreateBuildEnvironmentTask();

        this.taskList.add(buildScriptTask, directoryCreationTask);

    }

    @Override
    public void perform() {
        this.taskList.perform();
    }

    public static void run(String[] args) {
        try {
            ConsoleOutput.printBlankline();

            CreateNewProjectTask task = new CreateNewProjectTask(args);
            task.perform();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
