package ru.nsu.vetrov;

import java.time.LocalDate;

public class TaskConfig {
    private String id;
    private String name;
    private int maxPoints;
    private LocalDate softDeadline;
    private LocalDate hardDeadline;

    public TaskConfig(String id, String name, int maxPoints, LocalDate softDeadline, LocalDate hardDeadline) {
        this.id = id;
        this.name = name;
        this.maxPoints = maxPoints;
        this.softDeadline = softDeadline;
        this.hardDeadline = hardDeadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public LocalDate getSoftDeadline() {
        return softDeadline;
    }

    public void setSoftDeadline(LocalDate softDeadline) {
        this.softDeadline = softDeadline;
    }

    public LocalDate getHardDeadline() {
        return hardDeadline;
    }

    public void setHardDeadline(LocalDate hardDeadline) {
        this.hardDeadline = hardDeadline;
    }
}
