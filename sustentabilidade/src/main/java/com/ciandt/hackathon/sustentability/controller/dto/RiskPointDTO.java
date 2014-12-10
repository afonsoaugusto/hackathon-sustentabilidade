package com.ciandt.hackathon.sustentability.controller.dto;

import com.ciandt.hackathon.sustentability.model.RiskEntity;
import com.ciandt.hackathon.sustentability.service.enumaration.RiskLevel;

public class RiskPointDTO {

    private Double latitude;
    private Double longitude;
    private Float riskPercentage;
    private String riskType;

    public RiskPointDTO(RiskEntity risk) {

        this.latitude = risk.getLatitude();
        this.longitude = risk.getLongitude();
        this.riskPercentage = risk.getRiskPercentage();
        this.riskType = RiskLevel.GetRiskLevel(risk.getRiskPercentage()).toString();

    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Float getRiskPercentage() {
        return riskPercentage;
    }
    public void setRiskPercentage(Float riskPercentage) {
        this.riskPercentage = riskPercentage;
    }
    public String getRiskType() {
        return riskType;
    }
    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }


}
