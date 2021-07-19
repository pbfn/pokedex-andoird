package com.br.pedro.bruno.pokedex.model;

import java.util.ArrayList;

public class Pokemon {

    private int idPokemon;
    private String namePokemon;
    private String urlImagePokemon;
    private ArrayList<Stat> stats;
    private ArrayList<Type> types;

    public int getId() {
        return idPokemon;
    }

    public void setId(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getName() {
        return namePokemon;
    }

    public void setName(String namePokemon) {
        this.namePokemon = namePokemon;
    }

    public String getUrlImage() {
        return urlImagePokemon;
    }

    public void setUrlImage(String urlImagePokemon) {
        this.urlImagePokemon = urlImagePokemon;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stat> stats) {
        this.stats = stats;
    }
}
