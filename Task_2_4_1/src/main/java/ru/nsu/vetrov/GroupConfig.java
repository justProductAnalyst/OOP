package ru.nsu.vetrov;

import java.util.List;

public class GroupConfig {
    private String name;
    private List<StudentConfig> students;

    public GroupConfig(String name, List<StudentConfig> students) {
        this.name = name;
        this.students = students;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentConfig> getStudents() {
        return students;
    }

    public void setStudents(List<StudentConfig> students) {
        this.students = students;
    }
}
