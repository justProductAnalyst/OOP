package ru.nsu.vetrov;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitService {
    public void cloneRepository(String repoUrl, String destinationDir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "git", "clone", repoUrl, destinationDir
        );
        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new Exception("Failed to clone repository: " + repoUrl);
        }
    }

    public void checkoutBranch(String repoDir, String branchName) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "git", "-C", repoDir, "checkout", branchName
        );
        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new Exception("Failed to checkout branch: " + branchName);
        }
    }
}
