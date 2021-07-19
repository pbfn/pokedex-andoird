package com.br.pedro.bruno.pokedex.controller;

import android.content.ContentValues;
import android.content.Context;

import com.br.pedro.bruno.pokedex.datamodel.StatPokemonDataModel;
import com.br.pedro.bruno.pokedex.datamodel.TypePokemonDataModel;
import com.br.pedro.bruno.pokedex.datasource.AppDataBase;
import com.br.pedro.bruno.pokedex.model.StatPokemon;
import com.br.pedro.bruno.pokedex.model.TypePokemon;

import java.util.List;

public class StatPokemonController extends AppDataBase implements ICrud<StatPokemon>{

    ContentValues dadoDoObjeto;

    public StatPokemonController(Context context) {
        super(context);
    }


    @Override
    public boolean incluir(StatPokemon obj) {
        dadoDoObjeto= new ContentValues();
        //Key , valor

        dadoDoObjeto.put(StatPokemonDataModel.IDPOKEMON,obj.getIdPokemon());
        dadoDoObjeto.put(StatPokemonDataModel.IDSTAT,obj.getIdStat());
        dadoDoObjeto.put(StatPokemonDataModel.BASESTAT,obj.getBaseStat());
        //Enviar dados para a classe AppDataBase
        return insert(StatPokemonDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean alterar(StatPokemon obj) {
        return true;
    }

    @Override
    public boolean deletar(int id) {
        return true;
    }

    @Override
    public List<StatPokemon> listar() {
        return getAllStatsPokemon(StatPokemonDataModel.TABELA);
    }

    public List<StatPokemon> getByIdPokemon(int idPokemon){
        return getStatsPokemonById(StatPokemonDataModel.TABELA,idPokemon);
    }

}
