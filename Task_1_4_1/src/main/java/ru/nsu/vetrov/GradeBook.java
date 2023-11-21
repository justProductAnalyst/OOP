package ru.nsu.vetrov;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GradeBook {
    private String studentName;
    private int currentSemester;
    private List<Grade> grades;
    private Grade finalQualifyingWorkGrade;

    private static final int EXCELLENT_GRADE = 5;
    private static final int SATISFACTORY_GRADE = 3;

    public GradeBook(String studentName, int initialSemester) {
        this.studentName = studentName;
        this.currentSemester = initialSemester;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade, String subject, int semester) {
        grades.add(new Grade(grade, subject, semester));
    }

    public void setFinalQualifyingWorkGrade(int grade) {
        this.finalQualifyingWorkGrade = new Grade(grade, "Final Qualifying Work", currentSemester);
    }

    public double calculateAverageGrade() {
        return grades.stream().mapToInt(Grade::getGrade).average().orElse(0.0);
    }

    public boolean isEligibleForHonors() {
        long excellentGradesCount = grades.stream().filter(g -> g.getGrade() == EXCELLENT_GRADE).count();
        boolean hasSatisfactoryGrade = grades.stream().anyMatch(g -> g.getGrade() == SATISFACTORY_GRADE);
        boolean finalWorkIsExcellent = finalQualifyingWorkGrade != null && finalQualifyingWorkGrade.getGrade() == EXCELLENT_GRADE;

        return excellentGradesCount >= grades.size() * 0.75 && !hasSatisfactoryGrade && finalWorkIsExcellent;
    }

    public boolean isEligibleForIncreasedScholarship() {
        List<Grade> lastSemesterGrades = grades.stream()
                .filter(g -> g.getSemester() == currentSemester)
                .toList();

        return lastSemesterGrades.stream().allMatch(g -> g.getGrade() == EXCELLENT_GRADE);
    }

    public void incrementSemester() {
        currentSemester++;
    }

    private static class Grade {
        private int grade;
        private String subject;
        private int semester;

        public Grade(int grade, String subject, int semester) {
            this.grade = grade;
            this.subject = subject;
            this.semester = semester;
        }

        public int getGrade() {
            return grade;
        }

        public int getSemester() {
            return semester;
        }
    }
}
