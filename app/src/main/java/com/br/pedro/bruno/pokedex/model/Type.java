package com.br.pedro.bruno.pokedex.model;

import java.io.Serializable;

public class Type implements Serializable {

    private int idType;
    private String nameType;
    private String urlType;


    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

}
