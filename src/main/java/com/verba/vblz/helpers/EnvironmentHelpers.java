package com.verba.vblz.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class EnvironmentHelpers {
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

    public static String getCurrentFolder() {
        String path = System.getProperty("user.dir");
        String[] directories = path.split("/");

        return directories[directories.length - 1];
    }
}
