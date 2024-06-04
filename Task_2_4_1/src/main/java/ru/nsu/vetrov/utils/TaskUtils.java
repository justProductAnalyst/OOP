package ru.nsu.vetrov.utils;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import ru.nsu.vetrov.Student;
import ru.nsu.vetrov.Task;

public class TaskUtils {

    public static PassResults getPassResults(Task task, Student student, String repoPrefix) {
        boolean hardPass = false;
        boolean softPass = false;
        try {
            File repoFile = new File(String.format("%s/%s", repoPrefix, student.getNickname()));
            Iterable<RevCommit> commits = Git.open(repoFile).log().addPath(task.getName()).call();
            LocalDate first = null;
            int last = 0;
            for (RevCommit commit : commits) {
                if (first == null) {
                    first = LocalDate.ofInstant(
                            Instant.ofEpochSecond(commit.getCommitTime()), ZoneId.systemDefault()
                    );
                }
                last = commit.getCommitTime();
            }
            if (first == null) {
                System.out.println("No commits with this project found.");
                return new PassResults(false, false);
            }
            LocalDate lastDate = LocalDate.ofInstant(
                    Instant.ofEpochSecond(last), ZoneId.systemDefault());

            softPass = first.isBefore(task.getSoftDeadline());
            hardPass = lastDate.isBefore(task.getHardDeadline());
        } catch (Exception ignored) {
        }
        return new PassResults(softPass, hardPass);
    }

    public static class PassResults {
        private final boolean soft;
        private final boolean hard;

        public PassResults(boolean soft, boolean hard) {
            this.soft = soft;
            this.hard = hard;
        }

        public boolean soft() {
            return soft;
        }

        public boolean hard() {
            return hard;
        }
    }
}
