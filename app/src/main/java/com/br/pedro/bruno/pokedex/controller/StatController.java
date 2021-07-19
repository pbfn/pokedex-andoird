package com.br.pedro.bruno.pokedex.controller;

import android.content.ContentValues;
import android.content.Context;

import com.br.pedro.bruno.pokedex.datamodel.StatDataModel;
import com.br.pedro.bruno.pokedex.datamodel.TypeDataModel;
import com.br.pedro.bruno.pokedex.datasource.AppDataBase;
import com.br.pedro.bruno.pokedex.model.Stat;
import com.br.pedro.bruno.pokedex.model.Type;

import java.util.List;

public class StatController extends AppDataBase implements ICrud<Stat>{

    ContentValues dadoDoObjeto;

    public StatController(Context context) {
        super(context);
    }


    @Override
    public boolean incluir(Stat obj) {
        dadoDoObjeto= new ContentValues();
        //Key , valor

        dadoDoObjeto.put(StatDataModel.IDSTAT,obj.getIdStat());
        dadoDoObjeto.put(StatDataModel.NAMESTAT,obj.getNameStat());
        dadoDoObjeto.put(StatDataModel.URLSTAT,obj.getUrlStat());

        //Enviar dados para a classe AppDataBase

        return insert(StatDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean alterar(Stat obj) {
        dadoDoObjeto= new ContentValues();

        //Key , valor
        dadoDoObjeto.put(StatDataModel.IDSTAT,obj.getIdStat());
        dadoDoObjeto.put(StatDataModel.NAMESTAT,obj.getNameStat());
        dadoDoObjeto.put(StatDataModel.URLSTAT,obj.getUrlStat());

        //Enviar dados para a classe AppDataBase

        return updateById(StatDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean deletar(int id) {
        return deleteById(StatDataModel.TABELA,id);
    }

    @Override
    public List<Stat> listar() {
        return getAllStats(StatDataModel.TABELA);
    }

    public Stat getByName(String name){

        return getStatByName(StatDataModel.TABELA,name);
    }

}
