package com.br.pedro.bruno.pokedex.model;

import java.io.Serializable;

public class Stat implements Serializable {

    private int idStat;
    private String nameStat;
    private String urlStat;


    public int getIdStat() {
        return idStat;
    }

    public void setIdStat(int idStat) {
        this.idStat = idStat;
    }

    public String getNameStat() {
        return nameStat;
    }

    public void setNameStat(String nameStat) {
        this.nameStat = nameStat;
    }

    public String getUrlStat() {
        return urlStat;
    }

    public void setUrlStat(String urlStat) {
        this.urlStat = urlStat;
    }
}
