package ru.nsu.vetrov;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DocumentationService {
    public boolean generateDocumentation(String projectDir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "javadoc", "-d", projectDir + "/doc", projectDir + "/src/**/*.java"
        );
        builder.directory(new java.io.File(projectDir));
        Process process = builder.start();
        int exitCode = process.waitFor();
        return exitCode == 0;
    }
}
