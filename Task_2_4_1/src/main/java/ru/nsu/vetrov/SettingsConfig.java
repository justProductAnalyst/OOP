package ru.nsu.vetrov;

import java.util.Map;

public class SettingsConfig {
    private String scoringCriteria;
    private String timeoutStrategy;
    private Map<String, Integer> extraPoints;

    public SettingsConfig(String scoringCriteria, String timeoutStrategy, Map<String, Integer> extraPoints) {
        this.scoringCriteria = scoringCriteria;
        this.timeoutStrategy = timeoutStrategy;
        this.extraPoints = extraPoints;
    }

    // Getters and setters
    public String getScoringCriteria() {
        return scoringCriteria;
    }

    public void setScoringCriteria(String scoringCriteria) {
        this.scoringCriteria = scoringCriteria;
    }

    public String getTimeoutStrategy() {
        return timeoutStrategy;
    }

    public void setTimeoutStrategy(String timeoutStrategy) {
        this.timeoutStrategy = timeoutStrategy;
    }

    public Map<String, Integer> getExtraPoints() {
        return extraPoints;
    }

    public void setExtraPoints(Map<String, Integer> extraPoints) {
        this.extraPoints = extraPoints;
    }
}
