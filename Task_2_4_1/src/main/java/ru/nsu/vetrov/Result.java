package ru.nsu.vetrov;

public class Result {
    private boolean compiled;
    private boolean documented;
    private boolean styleChecked;
    private int testsPassed;
    private int testsFailed;
    private int testsSkipped;
    private int extraPoints;
    private int totalPoints;

    public Result(boolean compiled, boolean documented, boolean styleChecked, int testsPassed, int testsFailed, int testsSkipped, int extraPoints, int totalPoints) {
        this.compiled = compiled;
        this.documented = documented;
        this.styleChecked = styleChecked;
        this.testsPassed = testsPassed;
        this.testsFailed = testsFailed;
        this.testsSkipped = testsSkipped;
        this.extraPoints = extraPoints;
        this.totalPoints = totalPoints;
    }

    // Getters and setters
    public boolean isCompiled() {
        return compiled;
    }

    public void setCompiled(boolean compiled) {
        this.compiled = compiled;
    }

    public boolean isDocumented() {
        return documented;
    }

    public void setDocumented(boolean documented) {
        this.documented = documented;
    }

    public boolean isStyleChecked() {
        return styleChecked;
    }

    public void setStyleChecked(boolean styleChecked) {
        this.styleChecked = styleChecked;
    }

    public int getTestsPassed() {
        return testsPassed;
    }

    public void setTestsPassed(int testsPassed) {
        this.testsPassed = testsPassed;
    }

    public int getTestsFailed() {
        return testsFailed;
    }

    public void setTestsFailed(int testsFailed) {
        this.testsFailed = testsFailed;
    }

    public int getTestsSkipped() {
        return testsSkipped;
    }

    public void setTestsSkipped(int testsSkipped) {
        this.testsSkipped = testsSkipped;
    }

    public int getExtraPoints() {
        return extraPoints;
    }

    public void setExtraPoints(int extraPoints) {
        this.extraPoints = extraPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
