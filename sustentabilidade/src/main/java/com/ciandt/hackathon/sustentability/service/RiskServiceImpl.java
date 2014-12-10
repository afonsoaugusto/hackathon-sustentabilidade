package com.ciandt.hackathon.sustentability.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciandt.hackathon.sustentability.model.ProfileEntity;
import com.ciandt.hackathon.sustentability.model.RiskEntity;
import com.ciandt.hackathon.sustentability.repository.RiskRepository;
import com.googlecode.objectify.Key;

@Service
public class RiskServiceImpl implements RiskService {

	private static final Logger LOGGER = Logger.getLogger(RiskServiceImpl.class);

	@Autowired
	private RiskRepository riskRepository;


    @Override
    public RiskEntity saveRisk(RiskEntity risk, ProfileEntity loggedUser) {
        risk.setData(new Date());
        risk.setEmailUser(loggedUser.getEmail());

        if (!risk.getAterro()) {
            risk.setAnguloAterro(null);
        }

        if (!risk.getEncosta()) {
            risk.setAnguloEncosta(null);
        }

        if (!risk.getExisteVazamento().equalsIgnoreCase("sim")) {
            risk.setTipoVazamento(null);
        }

        if (!risk.getExisteTrincas()) {
            risk.setTipoTrinca(null);
        }

        risk.setRiskPercentage(new CalculatorRisk(risk).calculateRisk());

		return putAndGet(risk);

	}

	public RiskEntity putAndGet(RiskEntity profile) {
		Key<RiskEntity> id = riskRepository.put(profile);
		try {
			return riskRepository.get( id );
		} catch (Exception e) {
			return profile;
		}
	}

    @Override
    public RiskEntity updateRisk(RiskEntity risk) {
        return putAndGet(risk);
    }

    @Override
    public List<RiskEntity> findAllRisks() {
        return riskRepository.listAll();
    }


}
