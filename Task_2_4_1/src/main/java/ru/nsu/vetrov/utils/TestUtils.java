package ru.nsu.vetrov.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import ru.nsu.vetrov.Student;
import ru.nsu.vetrov.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static class TestCounts {
        private final int total;
        private final int fail;
        private final int skip;

        public TestCounts(int total, int fail, int skip) {
            this.total = total;
            this.fail = fail;
            this.skip = skip;
        }

        public int total() {
            return total;
        }

        public int fail() {
            return fail;
        }

        public int skip() {
            return skip;
        }
    }

    public static int getCoveragePercentage(File projectDir, String repoPrefix, Student student, Task task) {
        runGradleWrapperTask(projectDir, "jacocoTestReport");

        File jacocoFile = new File(
                String.format("%s/%s/%s/build/reports/jacoco/test/jacocoTestReport.xml", repoPrefix, student.getNickname(), task.getName())
        );

        try {
            Document report = Jsoup.parse(jacocoFile, "UTF-8", "", Parser.xmlParser());
            report.getElementsByTag("package").remove();
            float total = 0.0f;
            for (var el : report.getElementsByTag("counter")) {
                int covered = Integer.parseInt(el.attr("covered"));
                int missed = Integer.parseInt(el.attr("missed"));
                total += (float) covered / (covered + missed);
            }
            float coverage = total / report.getElementsByTag("counter").size();
            return Math.round(coverage * 100.0f);
        } catch (IOException e) {
            System.out.println("Failed to find jacoco test report: " + e);
            return 0;
        }
    }

    public static TestCounts getTestCounts(String repoPrefix, Student student, Task task) {
        File reportFile = new File(
                String.format("%s/%s/%s/build/reports/tests/test/index.html", repoPrefix, student.getNickname(), task.getName())
        );
        try {
            Document report = Jsoup.parse(reportFile, "UTF-8");
            int testCount = Integer.parseInt(report.getElementById("tests").getElementsByClass("counter").get(0).text());
            int failCount = Integer.parseInt(report.getElementById("failures").getElementsByClass("counter").get(0).text());
            int skipCount = Integer.parseInt(report.getElementById("ignored").getElementsByClass("counter").get(0).text());
            return new TestCounts(testCount, failCount, skipCount);
        } catch (IOException e) {
            System.out.println("Failed to find test report file: " + reportFile);
            return new TestCounts(0, 0, 0);
        }
    }

    public static boolean runGradleWrapperTask(File projectDir, String... tasks) {
        List<String> command = new ArrayList<>();
        command.add(isWindows() ? "gradlew.bat" : "./gradlew");
        command.addAll(Arrays.asList(tasks));

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(projectDir);
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to run gradle task: " + String.join(" ", tasks) + ". Error: " + e.getMessage());
            return false;
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
