package ru.nsu.vetrov;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.codehaus.groovy.control.CompilerConfiguration;
import ru.nsu.vetrov.utils.GitUtils;

public class Application {

    public static void main(String[] args) {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass(DelegatingScript.class.getName());
        ClassLoader loader = Application.class.getClassLoader();
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(loader, binding, config);

        CheckerConfig checkerConfig = null;

        try (InputStream inputStream = loader.getResourceAsStream("config.groovy")) {
            if (inputStream == null) {
                System.out.println("Error: No config found.");
                return;
            }
            DelegatingScript script = (DelegatingScript) shell.parse(
                    new InputStreamReader(inputStream));
            script.setBinding(binding);
            Object result = script.run();

            if (result instanceof CheckerConfig) {
                checkerConfig = (CheckerConfig) result;
            } else {
                System.out.println("Error: The script did not return a CheckerConfig object.");
                return;
            }

            System.out.println("Loaded tasks: " + checkerConfig.getTasks().size());
            for (Task task : checkerConfig.getTasks()) {
                System.out.println("Task: " + task.getName());
            }

            System.out.println("Loaded students: " + checkerConfig.getStudents().size());
            for (Student student : checkerConfig.getStudents()) {
                System.out.println("Student: " + student.getName());
            }

            processTasks(checkerConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processTasks(CheckerConfig checkerConfig) {
        String repoPrefix = "repositories";
        var results = new ArrayList<ArrayList<TaskResult>>();

        if (GitUtils.updateRepositories(checkerConfig, repoPrefix)) {
            return;
        }

        for (Task task : checkerConfig.getTasks()) {
            var taskResults = new ArrayList<TaskResult>();
            for (Student student : checkerConfig.getStudents()) {
                TaskRunner runner = new TaskRunner(repoPrefix, student, task);
                TaskResult result = runner.runTask();
                taskResults.add(result);
            }
            results.add(taskResults);
        }

        System.out.println(results);
        ReportGenerator.generate(results, checkerConfig);
    }
}
