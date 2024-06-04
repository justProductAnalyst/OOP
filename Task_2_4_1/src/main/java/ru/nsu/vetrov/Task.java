package ru.nsu.vetrov;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private int maxMark;
    private LocalDate softDeadline;
    private LocalDate hardDeadline;

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public void setSoftDeadline(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.softDeadline = LocalDate.parse(date, formatter);
    }

    public void setHardDeadline(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.hardDeadline = LocalDate.parse(date, formatter);
    }

    public String getName() {
        return name;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public LocalDate getSoftDeadline() {
        return softDeadline;
    }

    public LocalDate getHardDeadline() {
        return hardDeadline;
    }
}
