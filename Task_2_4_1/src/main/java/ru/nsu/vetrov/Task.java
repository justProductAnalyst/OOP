package ru.nsu.vetrov;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private int maxMark;
    private LocalDate softDeadline;
    private LocalDate hardDeadline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public LocalDate getSoftDeadline() {
        return softDeadline;
    }

    public void setSoftDeadline(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.softDeadline = LocalDate.parse(date, formatter);
    }

    public LocalDate getHardDeadline() {
        return hardDeadline;
    }

    public void setHardDeadline(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.hardDeadline = LocalDate.parse(date, formatter);
    }
}
