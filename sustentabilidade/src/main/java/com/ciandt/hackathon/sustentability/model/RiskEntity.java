package com.ciandt.hackathon.sustentability.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class RiskEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Date data;
    private String description;
    private String emailUser;
    private String photoKey;
    private Double latitude;
    private Double longitude;
    private String tipoMoradia;
    private Boolean aterro;
    private Boolean encosta;
    private Integer anguloEncosta;
    private Integer anguloAterro;
    private Boolean lixo;
    private Boolean concentracao;
    private String existeVazamento;
    private String tipoVazamento;
    private Boolean presencaArvore;
    private Boolean areaDesmatada;
    private Boolean vegetacaoRasteira;
    private Boolean areaCultivo;
    private String minasBarranco;
    private Boolean existeTrincas;
    private String tipoTrinca;
    private Float riskPercentage;
    private Integer numMoradias;
    private Integer numRemocao;
    private String outrasInformacoes;


    public RiskEntity () {

    }


    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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



    public String getTipoMoradia() {
        return tipoMoradia;
    }



    public void setTipoMoradia(String tipoMoradia) {
        this.tipoMoradia = tipoMoradia;
    }



    public Boolean getAterro() {
        return aterro;
    }



    public void setAterro(Boolean aterro) {
        this.aterro = aterro;
    }



    public Boolean getEncosta() {
        return encosta;
    }



    public void setEncosta(Boolean encosta) {
        this.encosta = encosta;
    }



    public Integer getAnguloEncosta() {
        return anguloEncosta;
    }



    public void setAnguloEncosta(Integer anguloEncosta) {
        this.anguloEncosta = anguloEncosta;
    }



    public Integer getAnguloAterro() {
        return anguloAterro;
    }



    public void setAnguloAterro(Integer anguloAterro) {
        this.anguloAterro = anguloAterro;
    }


    public Boolean getLixo() {
        return lixo;
    }



    public void setLixo(Boolean lixo) {
        this.lixo = lixo;
    }



    public Boolean getConcentracao() {
        return concentracao;
    }



    public void setConcentracao(Boolean concentracao) {
        this.concentracao = concentracao;
    }


    public String getTipoVazamento() {
        return tipoVazamento;
    }



    public void setTipoVazamento(String tipoVazamento) {
        this.tipoVazamento = tipoVazamento;
    }



    public Boolean getPresencaArvore() {
        return presencaArvore;
    }



    public void setPresencaArvore(Boolean presencaArvore) {
        this.presencaArvore = presencaArvore;
    }



    public Boolean getAreaDesmatada() {
        return areaDesmatada;
    }



    public void setAreaDesmatada(Boolean areaDesmatada) {
        this.areaDesmatada = areaDesmatada;
    }



    public Boolean getVegetacaoRasteira() {
        return vegetacaoRasteira;
    }



    public void setVegetacaoRasteira(Boolean vegetacaoRasteira) {
        this.vegetacaoRasteira = vegetacaoRasteira;
    }



    public Boolean getAreaCultivo() {
        return areaCultivo;
    }



    public void setAreaCultivo(Boolean areaCultivo) {
        this.areaCultivo = areaCultivo;
    }


    public String getMinasBarranco() {
        return minasBarranco;
    }


    public void setMinasBarranco(String minasBarranco) {
        this.minasBarranco = minasBarranco;
    }


    public void setExisteVazamento(String existeVazamento) {
        this.existeVazamento = existeVazamento;
    }


    public String getExisteVazamento() {
        return existeVazamento;
    }


    public Boolean getExisteTrincas() {
        return existeTrincas;
    }


    public void setExisteTrincas(Boolean existeTrincas) {
        this.existeTrincas = existeTrincas;
    }


    public String getTipoTrinca() {
        return tipoTrinca;
    }


    public void setTipoTrinca(String tipoTrinca) {
        this.tipoTrinca = tipoTrinca;
    }


    public Float getRiskPercentage() {
        return riskPercentage;
    }


    public Integer getNumMoradias() {
        return numMoradias;
    }


    public void setNumMoradias(Integer numMoradias) {
        this.numMoradias = numMoradias;
    }


    public Integer getNumRemocao() {
        return numRemocao;
    }


    public void setNumRemocao(Integer numRemocao) {
        this.numRemocao = numRemocao;
    }


    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }


    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }


    public void setRiskPercentage(Float riskPercentage) {
        this.riskPercentage = riskPercentage;
    }
}
