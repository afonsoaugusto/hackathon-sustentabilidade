package com.ciandt.hackathon.sustentability.service;

import java.util.List;

import com.ciandt.hackathon.sustentability.model.ProfileEntity;
import com.ciandt.hackathon.sustentability.model.RiskEntity;

public interface RiskService {

    RiskEntity saveRisk(RiskEntity risk, ProfileEntity loggedUser);

    RiskEntity updateRisk (RiskEntity risk);

    List<RiskEntity> findAllRisks();

}
