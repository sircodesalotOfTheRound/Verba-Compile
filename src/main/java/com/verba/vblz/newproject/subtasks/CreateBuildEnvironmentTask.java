package com.verba.vblz.newproject.subtasks;

import com.verba.tools.display.ConsoleOutput;
import com.verba.tools.tasks.Task;

import java.io.*;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class CreateBuildEnvironmentTask implements Task {
    private static final String helloWorldDotVFile =
        "                           \n" +
        "# Use the 'vm' namespace.  \n" +
        "withns vm                  \n" +
        "                           \n" +
        "# The program entry point. \n" +
        "fn main() {                \n" +
        "    print(\"hello world!\")\n" +
        "}                          \n" +
        "                           \n";

    public void perform() {
        createDir("code");
        createFile("code/hello_world.v", helloWorldDotVFile);

        createDir("test");
        createDir("build");

        createDir("resources");

        createDir("build/bin");
        createDir("build/symbols");

        ConsoleOutput.printBlankline();
    }

    private void createDir(String path) {
        new File(path).mkdir();
        ConsoleOutput.printlnOk("Created directory %s", path);
    }

    private void createFile(String path, String content) {
        try {
            File file = new File(path);
            file.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes(), 0, content.length());
            outputStream.close();

            ConsoleOutput.printlnOk("Created file %s", path);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
