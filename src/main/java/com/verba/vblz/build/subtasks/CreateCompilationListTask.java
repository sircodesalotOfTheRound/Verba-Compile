package com.verba.vblz.build.subtasks;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.verba.tools.EnvironmentHelpers;
import com.verba.tools.tasks.Task;

import java.io.File;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class CreateCompilationListTask implements Task {
    private final QList<File> files = new QList<>();

    public QIterable<File> listFiles() {
        File file = new File(EnvironmentHelpers.getCurrentFolderPath());
        return getVerbaCompilableFiles(file, new QList<>());
    }

    public QList<File> getVerbaCompilableFiles(File directory, QList<File> fileList) {
        QIterable<String> paths = new QList<String>(directory.list());

        // If there isn't anything to process, exit
        if (!paths.any()) {
            return fileList;
        }

        QIterable<File> pathsAsFiles = paths.map(File::new);

        for (File file : pathsAsFiles) {
            if (file.isDirectory()) {
                getVerbaCompilableFiles(file, fileList);
            }

            if (file.getAbsolutePath().endsWith(".v")) {
                fileList.add(file);
            }
        }

        return fileList;
    }

    @Override
    public void perform() {
        for (File file : listFiles()) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
