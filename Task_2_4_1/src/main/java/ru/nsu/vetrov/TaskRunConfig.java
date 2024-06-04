package ru.nsu.vetrov;

public class TaskRunConfig {
    private final String task;
    private final boolean excludeTests;

    public TaskRunConfig(String task) {
        this(task, false);
    }

    public TaskRunConfig(String task, boolean excludeTests) {
        this.task = task;
        this.excludeTests = excludeTests;
    }

    public String getTask() {
        return task;
    }

    public boolean excludeTests() {
        return excludeTests;
    }

    public TaskRunConfig withExcludeTests() {
        return new TaskRunConfig(task, true);
    }
}
