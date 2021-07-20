package com.br.pedro.bruno.pokedex.controller;

import android.content.ContentValues;
import android.content.Context;

import com.br.pedro.bruno.pokedex.datamodel.PokemonDataModel;
import com.br.pedro.bruno.pokedex.datamodel.TypePokemonDataModel;
import com.br.pedro.bruno.pokedex.datasource.AppDataBase;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.model.TypePokemon;

import java.util.List;

public class TypePokemonController extends AppDataBase implements ICrud<TypePokemon>{

    ContentValues dadoDoObjeto;

    public TypePokemonController(Context context) {
        super(context);
    }


    @Override
    public boolean incluir(TypePokemon obj) {
        dadoDoObjeto= new ContentValues();
        //Key , valor

        dadoDoObjeto.put(TypePokemonDataModel.IDPOKEMON,obj.getIdPokemon());
        dadoDoObjeto.put(TypePokemonDataModel.IDTYPE,obj.getIdType());
        dadoDoObjeto.put(TypePokemonDataModel.ORDER,obj.getOrder());
        //Enviar dados para a classe AppDataBase

        return insert(TypePokemonDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean alterar(TypePokemon obj) {
        return true;
    }

    @Override
    public boolean deletar(int id) {
        return true;
    }

    @Override
    public List<TypePokemon> listar() {
        return getAllTypesPokemon(TypePokemonDataModel.TABELA);
    }

    public List<TypePokemon> getByIdPokemon(int idPokemon){
        return getTypePokemonById(TypePokemonDataModel.TABELA,idPokemon);
    }

}
