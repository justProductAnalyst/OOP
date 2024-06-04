package ru.nsu.vetrov.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import ru.nsu.vetrov.CheckerConfig;
import ru.nsu.vetrov.Student;

public class GitUtils {

    public static boolean updateRepositories(CheckerConfig config, String repoPrefix) {
        String username = "justV";
        String token = "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";

        for (Student student : config.getStudents()) {
            try {
                File repoFile = new File(String.format("%s/%s",
                        repoPrefix, student.getNickname()));
                Git repo;
                String repoURI = String.format("https://%s:%s@github.com/%s/OOP",
                        username, token, student.getNickname());

                if (!repoFile.exists()) {
                    System.out.println("Cloning repository for student: " + student.getNickname());
                    repo = Git.cloneRepository()
                            .setURI(repoURI)
                            .setDirectory(repoFile)
                            .setCredentialsProvider(
                                    new UsernamePasswordCredentialsProvider(username, token))
                            .call();
                } else {
                    try {
                        System.out.println("Pulling latest changes for student: "
                                + student.getNickname());
                        repo = Git.open(repoFile);
                        repo.remoteSetUrl()
                                .setRemoteName("origin")
                                .setRemoteUri(new URIish(repoURI))
                                .call();
                        repo.pull()
                                .setCredentialsProvider(
                                        new UsernamePasswordCredentialsProvider(username, token))
                                .call();
                    } catch (IOException e) {
                        System.out.println("Error opening repository for student "
                                + student.getNickname() + ": " + e.getMessage());
                        return true;
                    }
                }
            } catch (GitAPIException | URISyntaxException e) {
                System.out.println("Failed to clone or pull repository for student "
                        + student.getNickname() + ": " + e.getMessage());
            }
        }
        return false;
    }
}
