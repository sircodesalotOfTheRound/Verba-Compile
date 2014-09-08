package com.verba.tools;

import com.javalinq.implementations.QList;
import com.javalinq.interfaces.QIterable;
import com.verba.tools.files.FileTools;

import java.io.*;
import java.util.function.Supplier;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class EnvironmentHelpers {
    private static final File buildRoot = new Supplier<File>() {
        @Override
        public File get() {
            File file = FileTools
                .findInParentFolders(System.getProperty("user.dir"),
                    folder -> {
                        QIterable<String> filesInFolder = new QList<>(folder.list());
                        return filesInFolder.any(fileName -> fileName.equals("vblz-build.xml"));
                    })
                .first();

            return file;
        }
    }.get();

    public static String getHostname() {
        try {
            Process process = Runtime.getRuntime().exec("hostname");
            InputStream inputStream = process.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);

            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "anonymous";
    }

    public static String getCurrentFolderName() {
        String path = System.getProperty("user.dir");
        String[] directories = path.split("/");

        return directories[directories.length - 1];
    }


    public static String getCodeFolderPath() {
        return String.format("%s/%s", EnvironmentHelpers.getCurrentFolderPath(), "code");
    }

    public static String getSymbolFolderPath() {
        return String.format("%s/%s/%s", EnvironmentHelpers.getCurrentFolderPath(), "build", "symbols");
    }

    public static String getCurrentFolderPath() {
        return buildRoot.getAbsolutePath();
    }


}
