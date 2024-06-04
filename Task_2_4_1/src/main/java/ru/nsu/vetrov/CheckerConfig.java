package ru.nsu.vetrov;

import groovy.lang.Closure;
import java.util.ArrayList;

public class CheckerConfig {
    private ArrayList<Task> tasks;
    private ArrayList<Student> students;

    public CheckerConfig() {
        tasks = new ArrayList<>();
        students = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void task(Closure<?> closure) {
        Task task = new Task();
        closure.setDelegate(task);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
        tasks.add(task);
        System.out.println("Task added: " + task.getName());
    }

    public void student(Closure<?> closure) {
        Student student = new Student();
        closure.setDelegate(student);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
        students.add(student);
        System.out.println("Student added: " + student.getName());
    }
}
