package ru.nsu.vetrov;

import java.io.PrintWriter;
import java.util.Map;

public class ReportService {
    public void generateReport(Group group, Map<Task, Map<Student, Result>> results) throws Exception {
        try (PrintWriter writer = new PrintWriter("report.html", "UTF-8")) {
            writer.println("<html>");
            writer.println("<head><title>OOP Checker Report</title></head>");
            writer.println("<body>");
            writer.println("<h1>Group " + group.getName() + "</h1>");

            for (Task task : results.keySet()) {
                writer.println("<h2>Task " + task.getId() + " (" + task.getName() + ")</h2>");
                writer.println("<table border='1'>");
                writer.println("<tr><th>Student</th><th>Compiled</th><th>Documented</th><th>Style Checked</th><th>Tests Passed</th><th>Tests Failed</th><th>Tests Skipped</th><th>Extra Points</th><th>Total Points</th></tr>");
                for (Student student : results.get(task).keySet()) {
                    Result result = results.get(task).get(student);
                    writer.println("<tr>");
                    writer.println("<td>" + student.getName() + "</td>");
                    writer.println("<td>" + (result.isCompiled() ? "+" : "-") + "</td>");
                    writer.println("<td>" + (result.isDocumented() ? "+" : "-") + "</td>");
                    writer.println("<td>" + (result.isStyleChecked() ? "+" : "-") + "</td>");
                    writer.println("<td>" + result.getTestsPassed() + "</td>");
                    writer.println("<td>" + result.getTestsFailed() + "</td>");
                    writer.println("<td>" + result.getTestsSkipped() + "</td>");
                    writer.println("<td>" + result.getExtraPoints() + "</td>");
                    writer.println("<td>" + result.getTotalPoints() + "</td>");
                    writer.println("</tr>");
                }
                writer.println("</table>");
            }

            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
