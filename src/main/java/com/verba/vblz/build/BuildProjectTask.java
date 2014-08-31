package com.verba.vblz.build;

import com.verba.tools.tasks.Task;
import com.verba.vblz.build.objectfile.SourceFilePathInfo;
import com.verba.vblz.build.objectfile.SymbolFileCreationTask;
import com.verba.vblz.build.subtasks.CreateCompilationListTask;

/**
 * Created by sircodesalot on 14/8/30.
 */
public class BuildProjectTask implements Task {
    private final CreateCompilationListTask createCompilationListTask = new CreateCompilationListTask();

    private void createSymbolFiles(Iterable<SourceFilePathInfo> sourceFiles) {
        for (SourceFilePathInfo sourceFile : sourceFiles) {
            SymbolFileCreationTask createSymbolFileTask = new SymbolFileCreationTask(sourceFile);
            createSymbolFileTask.perform();
        }
    }

    @Override
    public void perform() {
        createCompilationListTask.perform();
        createSymbolFiles(createCompilationListTask.files());
    }
}
