package ru.nsu.vetrov;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BuildService {
    public boolean compileProject(String projectDir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "javac", "-d", projectDir + "/bin", projectDir + "/src/**/*.java"
        );
        builder.directory(new java.io.File(projectDir));
        Process process = builder.start();
        int exitCode = process.waitFor();
        return exitCode == 0;
    }
}
