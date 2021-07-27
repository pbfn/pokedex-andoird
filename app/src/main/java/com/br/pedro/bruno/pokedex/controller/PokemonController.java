package com.br.pedro.bruno.pokedex.controller;

import android.content.ContentValues;
import android.content.Context;

import com.br.pedro.bruno.pokedex.datamodel.PokemonDataModel;
import com.br.pedro.bruno.pokedex.datasource.AppDataBase;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.model.Pokemon;

import java.util.List;

public class PokemonController extends AppDataBase implements ICrud<Pokemon>{

    ContentValues dadoDoObjeto;

    public PokemonController(Context context) {
        super(context);
    }


    @Override
    public boolean incluir(Pokemon obj) {
        dadoDoObjeto= new ContentValues();
        //Key , valor

        dadoDoObjeto.put(PokemonDataModel.IDPOKEMON,obj.getId());
        dadoDoObjeto.put(PokemonDataModel.NAMEPOKEMON,obj.getName());
        dadoDoObjeto.put(PokemonDataModel.URLIMAGEPOKEMON,obj.getUrlImage());
        dadoDoObjeto.put(PokemonDataModel.ISFAVORITE,obj.getIsFavorite());
        dadoDoObjeto.put(PokemonDataModel.BACKGROUNDCOLOR,obj.getBackgroundColor());

        //Enviar dados para a classe AppDataBase

        return insert(PokemonDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean alterar(Pokemon obj) {
        dadoDoObjeto= new ContentValues();

        //Key , valor
        dadoDoObjeto.put(PokemonDataModel.IDPOKEMON,obj.getId());
        dadoDoObjeto.put(PokemonDataModel.NAMEPOKEMON,obj.getName());
        dadoDoObjeto.put(PokemonDataModel.URLIMAGEPOKEMON,obj.getUrlImage());
        dadoDoObjeto.put(PokemonDataModel.ISFAVORITE,obj.getIsFavorite());
        dadoDoObjeto.put(PokemonDataModel.BACKGROUNDCOLOR,obj.getBackgroundColor());

        //Enviar dados para a classe AppDataBase

        return updateById(PokemonDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean deletar(int id) {
        return deleteById(PokemonDataModel.TABELA,id);
    }

    @Override
    public List<Pokemon> listar() {
        return getAllPokemons(PokemonDataModel.TABELA);
    }

    public List<Pokemon> listarFavoritos() {
        return getFavoritesPokemons(PokemonDataModel.TABELA);
    }

    public Pokemon getById(int id){
        return getPokemonById(PokemonDataModel.TABELA,id);
    }


}
