package ru.nsu.vetrov;

import java.util.List;
import java.util.Map;

public class Student {
    private String github;
    private String name;
    private String repo;
    private Map<Task, Result> results;

    public Student(String github, String name, String repo) {
        this.github = github;
        this.name = name;
        this.repo = repo;
    }

    // Getters and setters
    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public Map<Task, Result> getResults() {
        return results;
    }

    public void setResults(Map<Task, Result> results) {
        this.results = results;
    }
}
