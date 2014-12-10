package com.ciandt.hackathon.sustentability.service;

import com.ciandt.hackathon.sustentability.model.RiskEntity;

public class CalculatorRisk {

    private RiskEntity risk;

    public CalculatorRisk(RiskEntity risk) {
        if (risk == null) {
            throw new IllegalArgumentException("parametro risk nao pode ser null");
        }

        this.risk = risk;
    }

    public Float calculateRisk() {

        float risco = this.RiskOfLivingType();
        risco += this.RiskOfEncostoNatural();
        risco += this.RiskOfAterroLancado();
        risco += this.RiskOfBarranco();
        risco += this.RiskOfConcentration();
        risco += this.RiskOfVazamento();
        risco += this.RiskOfHasGarbage();
        risco += this.RiskOfPlantation();
        risco += this.RiskOfTrincas();

        return (risco * 100) / 46;
    }

    private int RiskOfLivingType() {
        switch (risk.getTipoMoradia()) {
        case "naoinformado":
        case "misto":
            return 2;
        case "alvenaria":
            return 1;
        case "madeira":
            return 3;
        default:
            throw new IllegalArgumentException("Valor do risco não é valido " + risk.getTipoMoradia());
        }
    }

    private int RiskOfEncostoNatural() {
        if (!risk.getEncosta()) {
            return 0;
        }
        switch (risk.getAnguloEncosta()) {
        case 90:
            return 5;
        case 60:
            return 4;
        case 30:
            return 3;
        case 17:
            return 2;
        case 10:
            return 1;
        default:
            throw new IllegalArgumentException("Valor do angulo de encosta não é valido " + risk.getAnguloEncosta());
        }
    }

    private int RiskOfAterroLancado() {
        if (!risk.getAterro()) {
            return 0;
        }
        switch (risk.getAnguloAterro()) {
        case 90:
            return 5;
        case 60:
            return 4;
        case 30:
            return 3;
        case 17:
            return 2;
        case 10:
            return 1;
        default:
            throw new IllegalArgumentException("Valor do angulo de aterro não é valido " + risk.getAnguloAterro());
        }
    }

    private int RiskOfHasGarbage() {
        if (!risk.getLixo()) {
            return 0;
        } else {
            return 5;
        }
    }

    private int RiskOfConcentration() {
        if (!risk.getConcentracao()) {
            return 0;
        } else {
            return 5;
        }
    }

    private int RiskOfVazamento() {

        switch (risk.getExisteVazamento()) {
        case "naoinformado":
            return 2;
        case "sim":
            if(risk.getTipoVazamento().equalsIgnoreCase("esgoto")){
                return 4;
            }else{
                return 3;
            }
        case "nao":
            return 0;
        default:
            throw new IllegalArgumentException("Valor do risco de vazamento não é valido " + risk.getExisteVazamento());
        }
    }

    private int RiskOfBarranco() {

        switch (risk.getMinasBarranco()) {
        case "naoinformado":
            return 2;
        case "nope":
            return 1;
        case "nomeio":
            return 2;
        case "topo":
            return 3;
        default:
            throw new IllegalArgumentException("Valor das minas no barranco não é valido " + risk.getMinasBarranco());
        }
    }

    private int RiskOfPlantation() {
        int valorRisk = 0;
        if (risk.getPresencaArvore()) {
            valorRisk += 1;
        }
        if (risk.getVegetacaoRasteira()) {
            valorRisk += 2;
        }
        if(risk.getAreaDesmatada()) {
            valorRisk += 3;
        }
        if(risk.getAreaCultivo()) {
            valorRisk += 2;
        }
        return valorRisk;
   }

    private int RiskOfTrincas() {
        if (!risk.getExisteTrincas()) {
            return 0;
        }
        switch (risk.getTipoTrinca()) {
        case "naoInformado":
            return 4;
        case "noTerreno":
            return 3;
        case "naMoradia":
            return 5;
        default:
            throw new IllegalArgumentException("Valor do rsico das trincas não é valido " + risk.getAnguloAterro());
        }
    }
}
