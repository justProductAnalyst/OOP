package ru.nsu.vetrov.utils;

import com.puppycrawl.tools.checkstyle.Main;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.codehaus.groovy.control.CompilerConfiguration;
import ru.nsu.vetrov.CheckstyleResult;
import ru.nsu.vetrov.Student;
import ru.nsu.vetrov.Task;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;

public class CheckstyleUtils {

    public static CheckstyleResult getCheckstyleResult(Task task,
                                                       Student student, String repoPrefix) {
        CheckstyleResult checkstyle = CheckstyleResult.CLEAN;
        try {
            String outFile = "checkstyle.txt";
            int exitCode = catchSystemExit(() -> {
                try {
                    String configPath = String.format(
                            "%s/%s/.github/google_checks.xml",
                            repoPrefix, student.getNickname()
                    );
                    String mainSourcePath = String.format(
                            "%s/%s/%s/src/main/java/",
                            repoPrefix, student.getNickname(), task.getName()
                    );
                    String testSourcePath = String.format(
                            "%s/%s/%s/src/test/java/",
                            repoPrefix, student.getNickname(), task.getName()
                    );
                    Main.main("-c", configPath, "-o",
                            outFile, mainSourcePath, testSourcePath);
                } catch (IOException e) {
                    System.out.println("Failed to call checkstyle: " + e);
                }
            });

            int warnCount = 0;
            try (InputStream reader = new FileInputStream(outFile)) {
                Scanner scanner = new Scanner(reader);
                int cnt = 0;
                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                    cnt += 1;
                }
                warnCount = cnt - 2;
            } catch (IOException e) {
                System.out.println("Failed to read checkstyle's output");
            }
            if (exitCode != 0) {
                checkstyle = CheckstyleResult.ERROR;
            } else if (warnCount > 0) {
                checkstyle = CheckstyleResult.WARNING;
            }
        } catch (Exception e) {
            System.out.println("Exit should have been called... No checkstyle data, sry");
        }
        return checkstyle;
    }
}
