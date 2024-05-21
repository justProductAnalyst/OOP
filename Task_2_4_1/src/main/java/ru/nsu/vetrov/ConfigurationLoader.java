package ru.nsu.vetrov;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import groovy.util.ConfigObject;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigurationLoader {
    private static Map<String, Task> tasks = new HashMap<>();
    private static Map<String, Group> groups = new HashMap<>();

    public static void loadConfiguration(String configFilePath) throws IOException {
        File configFile = new File(configFilePath);
        String configContent = new String(Files.readAllBytes(configFile.toPath()));

        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate(configContent);

        loadTasks((Map<String, Map<String, Object>>) binding.getVariable("tasks"));
        loadGroups((Map<String, Map<String, Object>>) binding.getVariable("groups"));
    }

    private static void loadTasks(Map<String, Map<String, Object>> tasksConfig) {
        for (String taskKey : tasksConfig.keySet()) {
            Map<String, Object> taskConfig = tasksConfig.get(taskKey);
            Task task = new Task(
                    (String) taskConfig.get("id"),
                    (String) taskConfig.get("name"),
                    (Integer) taskConfig.get("maxPoints"),
                    LocalDate.parse((String) taskConfig.get("softDeadline")),
                    LocalDate.parse((String) taskConfig.get("hardDeadline"))
            );
            tasks.put(task.getId(), task);
        }
    }

    private static void loadGroups(Map<String, Map<String, Object>> groupsConfig) {
        for (String groupKey : groupsConfig.keySet()) {
            Map<String, Object> groupConfig = groupsConfig.get(groupKey);
            String groupName = (String) groupConfig.get("name");
            List<Student> students = ((Map<String, Map<String, Object>>) groupConfig.get("students")).values().stream()
                    .map(studentConfig -> new Student(
                            (String) studentConfig.get("github"),
                            (String) studentConfig.get("name"),
                            (String) studentConfig.get("repo")
                    )).collect(Collectors.toList());
            Group group = new Group(groupName, students);
            groups.put(group.getName(), group);
        }
    }

    public static Map<String, Task> getTasks() {
        return tasks;
    }

    public static Map<String, Group> getGroups() {
        return groups;
    }
}
