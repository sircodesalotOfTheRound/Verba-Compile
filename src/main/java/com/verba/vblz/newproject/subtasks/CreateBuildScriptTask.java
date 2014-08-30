package com.verba.vblz.newproject.subtasks;

import com.verba.tools.display.ConsoleOutput;
import com.verba.tools.tasks.Task;
import com.verba.tools.xml.XmlElement;
import com.verba.tools.xml.XmlSpace;

import java.io.*;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class CreateBuildScriptTask implements Task {
    private final String author;
    private final String name;
    private final String version;
    private final XmlElement buildScript;

    public CreateBuildScriptTask(String author, String name, String version) {
        this.author = author;
        this.name = name;
        this.version = version;
        this.buildScript = createBuildScript();
    }

    private XmlElement createBuildScript() {
        return
            new XmlElement("configuration",
                new XmlSpace(),
                new XmlElement("build",
                    new XmlElement("author", author),
                    new XmlElement("name", name),
                    new XmlElement("version", version),
                    new XmlSpace(),
                        new XmlElement("binaries",
                            new XmlElement("make", "dev"),
                            new XmlElement("make", "debug"),
                            new XmlElement("make", "release")),
                        new XmlSpace()),
                    new XmlSpace());
    }

    public void perform() {
        try {
            File file = new File("vblz-build.xml");
            FileOutputStream outputStream = new FileOutputStream(file);

            String buildScriptContent = String.format("%s\n", buildScript.toString());

            ConsoleOutput.printlnOk("Creating vblz-build.xml script");
            ConsoleOutput.printlnOk("Created vblz-build.xml script");

            ConsoleOutput.printBlankline();
            ConsoleOutput.printlnIndented(2, "Generated Build Script:");
            ConsoleOutput.printBlankline();
            ConsoleOutput.println(buildScript.getContentIndented(4));

            outputStream.write(buildScriptContent.getBytes(), 0, buildScriptContent.length());

            outputStream.close();

            ConsoleOutput.printBlankline();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}