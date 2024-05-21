package ru.nsu.vetrov;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StyleCheckService {
    public boolean checkStyle(String projectDir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "java", "-jar", "google-java-format.jar", "--replace", projectDir + "/src/**/*.java"
        );
        builder.directory(new java.io.File(projectDir));
        Process process = builder.start();
        int exitCode = process.waitFor();
        return exitCode == 0;
    }
}
