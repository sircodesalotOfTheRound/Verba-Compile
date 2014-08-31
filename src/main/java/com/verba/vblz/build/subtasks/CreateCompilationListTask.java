package com.verba.vblz.build.subtasks;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.verba.language.symbols.resolution.interfaces.SymbolResolutionInfo;
import com.verba.tools.EnvironmentHelpers;
import com.verba.tools.tasks.Task;
import com.verba.vblz.build.objectfile.SourceFilePathInfo;

import java.io.File;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class CreateCompilationListTask implements Task {
    private QIterable<SourceFilePathInfo> files;

    public QIterable<SourceFilePathInfo> listFiles() {
        File file = new File(String.format("%s/%s", EnvironmentHelpers.getCurrentFolderPath(), "code"));
        return getVerbaCompilableFiles(file, new QList<>());
    }

    public QIterable<SourceFilePathInfo> getVerbaCompilableFiles(File directory, QList<SourceFilePathInfo> fileList) {
        QIterable<File> paths = new QList<>(directory.listFiles());

        // If there isn't anything to process, exit
        if (!paths.any()) {
            return fileList;
        }

        for (File file : paths) {
            if (file.isDirectory()) {
                getVerbaCompilableFiles(file, fileList);
            }

            if (file.getAbsolutePath().endsWith(".v")) {
                fileList.add(new SourceFilePathInfo(file));
            }
        }

        return fileList;
    }

    @Override
    public void perform() {
        this.files = listFiles();

        for (SourceFilePathInfo file : files) {
            System.out.println(file);
            System.out.println("output path: " + file.outputPath());
        }
    }

    public QIterable<SourceFilePathInfo> files() { return this.files; }
}
