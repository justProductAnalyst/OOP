package ru.nsu.vetrov;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ru.nsu.vetrov.utils.CheckstyleUtils;
import ru.nsu.vetrov.utils.TaskUtils;
import ru.nsu.vetrov.utils.TestUtils;

public class TaskRunner {
    private final String repoPrefix;
    private final Student student;
    private final Task task;

    public TaskRunner(String repoPrefix, Student student, Task task) {
        this.repoPrefix = repoPrefix;
        this.student = student;
        this.task = task;
    }

    public TaskResult runTask() {
        System.out.println("Checking task " + task.getName() + "@" + student.getNickname());
        File projectFile = new File(String.format("%s/%s/%s",
                repoPrefix, student.getNickname(), task.getName()));

        if (!projectFile.exists()) {
            System.out.println("Project directory not found for "
                    + student.getNickname() + " and task " + task.getName());
            return new TaskResult(student,
                    false,
                    0,
                    0,
                    0,
                    0, CheckstyleResult.CLEAN, false, false);
        }

        boolean builds = runGradleWrapperTask(projectFile, "build", "-x", "test");
        boolean tests = runGradleWrapperTask(projectFile, "test");
        TestUtils.TestCounts counts = TestUtils.getTestCounts(repoPrefix, student, task);
        int coveragePercent = 0;
        try {
            coveragePercent = tests ? TestUtils.getCoveragePercentage(projectFile,
                    repoPrefix, student, task) : 0;
        } catch (Exception e) {
            System.out.println("Error while getting coverage percentage: " + e.getMessage());
        }
        CheckstyleResult checkstyle = CheckstyleUtils.getCheckstyleResult(task,
                student, repoPrefix);
        runGradleWrapperTask(projectFile, "javadoc");
        TaskUtils.PassResults passes = TaskUtils.getPassResults(task, student, repoPrefix);

        return new TaskResult(student, builds, counts.total(),
                counts.fail(), counts.skip(),
                coveragePercent, checkstyle, passes.soft(), passes.hard());
    }

    private boolean runGradleWrapperTask(File projectDir, String... tasks) {
        List<String> command = new ArrayList<>();
        command.add(isWindows() ? "gradlew.bat" : "./gradlew");
        command.addAll(Arrays.asList(tasks));

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(projectDir);
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("Failed to run gradle task: "
                        + String.join(" ", tasks));
            }
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to run gradle task: "
                    + String.join(" ", tasks) + ". Error: " + e.getMessage());
            return false;
        }
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
