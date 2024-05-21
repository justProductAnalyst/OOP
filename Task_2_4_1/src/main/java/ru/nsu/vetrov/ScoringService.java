package ru.nsu.vetrov;

public class ScoringService {
    public int calculateScore(Task task, Result result, int extraPoints) {
        int score = 0;

        if (result != null) {
            if (result.isCompiled() && result.isDocumented() && result.isStyleChecked()) {
                score = result.getTestsPassed() * (task.getMaxPoints() / (result.getTestsPassed() + result.getTestsFailed()));
            }
            score += extraPoints;
        }

        return score;
    }
}
