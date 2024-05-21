package ru.nsu.vetrov;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar oop-checker.jar <config-file-path>");
            System.exit(1);
        }

        String configFilePath = args[0];
        try {
            ConfigurationLoader.loadConfiguration(configFilePath);

            Map<String, Task> tasks = ConfigurationLoader.getTasks();
            Map<String, Group> groups = ConfigurationLoader.getGroups();

            GitService gitService = new GitService();
            BuildService buildService = new BuildService();
            DocumentationService docService = new DocumentationService();
            StyleCheckService styleService = new StyleCheckService();
            TestService testService = new TestService();
            ScoringService scoringService = new ScoringService();
            ReportService reportService = new ReportService();

            Map<Task, Map<Student, Result>> allResults = new HashMap<>();

            for (Group group : groups.values()) {
                for (Student student : group.getStudents()) {
                    String studentDir = "./repos/" + student.getGithub();
                    gitService.cloneRepository(student.getRepo(), studentDir);

                    for (Task task : tasks.values()) {
                        gitService.checkoutBranch(studentDir, "main");

                        boolean compiled = buildService.compileProject(studentDir);
                        boolean documented = compiled && docService.generateDocumentation(studentDir);
                        boolean styleChecked = documented && styleService.checkStyle(studentDir);
                        TestService.TestResult testResult = styleChecked ? testService.runTests(studentDir) : new TestService.TestResult(0, 0, 0, false);

                        Result result = new Result(
                                compiled,
                                documented,
                                styleChecked,
                                testResult.getTestsPassed(),
                                testResult.getTestsFailed(),
                                testResult.getTestsSkipped(),
                                0,
                                0
                        );

                        int totalPoints = scoringService.calculateScore(task, result, 0);
                        result.setTotalPoints(totalPoints);

                        allResults.computeIfAbsent(task, k -> new HashMap<>()).put(student, result);
                    }
                }
            }

            Group group = groups.values().iterator().next();
            reportService.generateReport(group, allResults);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}