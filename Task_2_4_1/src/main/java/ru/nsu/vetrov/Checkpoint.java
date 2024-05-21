package ru.nsu.vetrov;

import java.time.LocalDate;

public class Checkpoint {
    private String name;
    private LocalDate date;

    public Checkpoint(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
