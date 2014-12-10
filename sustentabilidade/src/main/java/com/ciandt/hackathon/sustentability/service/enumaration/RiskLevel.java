package com.ciandt.hackathon.sustentability.service.enumaration;

/**
 * Enum com os possiveis niveis de risco
 * @author cit
 */
public enum RiskLevel {

    LOW(0, 25), MEDIUM(25, 50), HIGH(50, 75), HIGHER(75, 100);

    private static final int MIN_VALUE_ALLOWED = 0;
    private static final int MAX_VALUE_ALLOWED = 100;


    private RiskLevel(float min, float max) {
        this.maxRange = max;
        this.minRange = min;
    }

    private float minRange;
    private float maxRange;


    public float getMinRange() {
        return minRange;
    }
    public void setMinRange(float minRange) {
        this.minRange = minRange;
    }
    public float getMaxRange() {
        return maxRange;
    }
    public void setMaxRange(float maxRange) {
        this.maxRange = maxRange;
    }

    public static RiskLevel GetRiskLevel(float value) {
        if (value < MIN_VALUE_ALLOWED && value > MAX_VALUE_ALLOWED) {
            throw new IllegalArgumentException(String.format("Valor %s de risco não é valido", value));
        }
        if (value <= LOW.maxRange) {
            return LOW;
        }
        if (value <= MEDIUM.maxRange) {
            return MEDIUM;
        }
        if (value <= HIGH.maxRange) {
            return HIGH;
        }
        return HIGHER;
    }
}
