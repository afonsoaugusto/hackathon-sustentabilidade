package com.ciandt.hackathon.sustentability.calculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.util.Assert;

import com.ciandt.hackathon.sustentability.model.RiskEntity;
import com.ciandt.hackathon.sustentability.service.CalculatorRisk;
import com.ciandt.hackathon.sustentability.service.enumaration.RiskLevel;

public class CalculatorValueRiskTest {



    CalculatorRisk cr;
    RiskEntity risk;


    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void iniciar() {
        risk = new RiskEntity();
        cr = new CalculatorRisk(risk);
    }

    @Test
    public void isLowRisk() throws Exception {
        popularRisk(RiskLevel.LOW);
        Float result = cr.calculateRisk();
        Assert.isTrue(RiskLevel.GetRiskLevel(result).equals(RiskLevel.LOW));
    }

    @Test
    public void isMediumRisk() throws Exception {
        popularRisk(RiskLevel.MEDIUM);
        Float result = cr.calculateRisk();
        Assert.isTrue(RiskLevel.GetRiskLevel(result).equals(RiskLevel.MEDIUM));
    }


    @Test
    public void isHighRisk() throws Exception {
        popularRisk(RiskLevel.HIGH);
        Float result = cr.calculateRisk();
        Assert.isTrue(RiskLevel.GetRiskLevel(result).equals(RiskLevel.HIGH));
    }

    @Test
    public void isHigherRisk() throws Exception {
        popularRisk(RiskLevel.HIGHER);
        Float result = cr.calculateRisk();
        Assert.isTrue(RiskLevel.GetRiskLevel(result).equals(RiskLevel.HIGHER));
    }


    @Test(expected=IllegalArgumentException.class)
    public void expectedException() throws Exception {
        popularRisk(RiskLevel.LOW);
        risk.setExisteVazamento("invalidValue");
        Float result = cr.calculateRisk();
        Assert.isTrue(RiskLevel.GetRiskLevel(result).equals(RiskLevel.HIGHER));
    }


    private void popularRisk(RiskLevel riskLevel) {
        switch (riskLevel) {
        case LOW:
            risk.setTipoMoradia("alvenaria");
            risk.setEncosta(false);
            risk.setAterro(false);
            risk.setLixo(false);
            risk.setConcentracao(false);
            risk.setExisteVazamento("nao");
            risk.setMinasBarranco("nope");
            risk.setPresencaArvore(false);
            risk.setAreaDesmatada(false);
            risk.setAreaCultivo(false);
            risk.setVegetacaoRasteira(false);
            risk.setExisteTrincas(false);

            break;

        case MEDIUM:
            risk.setTipoMoradia("madeira");
            risk.setEncosta(true);
            risk.setAnguloEncosta(90);
            risk.setAterro(true);
            risk.setAnguloAterro(90);
            risk.setLixo(false);
            risk.setConcentracao(false);
            risk.setExisteVazamento("nao");
            risk.setMinasBarranco("nope");
            risk.setPresencaArvore(false);
            risk.setAreaDesmatada(false);
            risk.setAreaCultivo(false);
            risk.setVegetacaoRasteira(false);
            risk.setExisteTrincas(false);
            break;

        case HIGH:
            risk.setTipoMoradia("madeira");
            risk.setEncosta(true);
            risk.setAnguloEncosta(90);
            risk.setAterro(true);
            risk.setAnguloAterro(90);
            risk.setLixo(true);
            risk.setConcentracao(true);
            risk.setExisteVazamento("nao");
            risk.setMinasBarranco("nope");
            risk.setPresencaArvore(false);
            risk.setAreaDesmatada(false);
            risk.setAreaCultivo(false);
            risk.setVegetacaoRasteira(false);
            risk.setExisteTrincas(false);
            break;

        case HIGHER:
            risk.setTipoMoradia("madeira");
            risk.setEncosta(true);
            risk.setAnguloEncosta(90);
            risk.setAterro(true);
            risk.setAnguloAterro(90);
            risk.setLixo(true);
            risk.setConcentracao(true);
            risk.setExisteVazamento("nao");
            risk.setMinasBarranco("nope");
            risk.setPresencaArvore(true);
            risk.setAreaDesmatada(true);
            risk.setAreaCultivo(true);
            risk.setVegetacaoRasteira(true);
            risk.setExisteTrincas(true);
            risk.setTipoTrinca("noTerreno");
            break;

        default:
            break;
        }
    }
}
