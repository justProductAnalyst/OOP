package ru.nsu.vetrov;

public class StudentConfig {
    private String github;
    private String name;
    private String repo;

    public StudentConfig(String github, String name, String repo) {
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
}
