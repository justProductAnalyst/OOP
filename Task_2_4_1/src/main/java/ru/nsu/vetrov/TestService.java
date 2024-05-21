package ru.nsu.vetrov;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestService {

    public TestResult runTests(String projectDir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "java", "-cp", projectDir + "/bin:lib/*", "org.junit.runner.JUnitCore", "MyTests"
        );
        builder.directory(new java.io.File(projectDir));
        Process process = builder.start();
        int exitCode = process.waitFor();

        int testsPassed = 0;
        int testsFailed = 0;
        int testsSkipped = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            // implement processing
        }

        return new TestResult(testsPassed, testsFailed, testsSkipped, exitCode == 0);
    }

    public static class TestResult {
        private int testsPassed;
        private int testsFailed;
        private int testsSkipped;
        private boolean allTestsPassed;

        public TestResult(int testsPassed, int testsFailed, int testsSkipped, boolean allTestsPassed) {
            this.testsPassed = testsPassed;
            this.testsFailed = testsFailed;
            this.testsSkipped = testsSkipped;
            this.allTestsPassed = allTestsPassed;
        }

        public int getTestsPassed() {
            return testsPassed;
        }

        public int getTestsFailed() {
            return testsFailed;
        }

        public int getTestsSkipped() {
            return testsSkipped;
        }

        public boolean isAllTestsPassed() {
            return allTestsPassed;
        }
    }
}
