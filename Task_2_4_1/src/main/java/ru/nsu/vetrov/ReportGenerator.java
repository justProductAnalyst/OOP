package ru.nsu.vetrov;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReportGenerator {

    public static void generate(ArrayList<ArrayList<TaskResult>> results, CheckerConfig config) {
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(new FileTemplateResolver());
        Context ctx = new Context();
        ctx.setVariable("results", results);
        ctx.setVariable("tasks", config.getTasks());

        File report = new File("report.html");
        try (var writer = new FileOutputStream(report)) {
            String result = engine.process("src/main/resources/reportTemplate.html", ctx);
            writer.write(result.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("Failed to write report: " + e);
        }
    }
}
