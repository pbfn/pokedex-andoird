package com.br.pedro.bruno.pokedex.controller;

import android.content.ContentValues;
import android.content.Context;


import com.br.pedro.bruno.pokedex.datamodel.TypeDataModel;
import com.br.pedro.bruno.pokedex.datasource.AppDataBase;
import com.br.pedro.bruno.pokedex.model.Type;

import java.util.List;

public class TypeController extends AppDataBase implements ICrud<Type>{

    ContentValues dadoDoObjeto;

    public TypeController(Context context) {
        super(context);
    }


    @Override
    public boolean incluir(Type obj) {
        dadoDoObjeto= new ContentValues();
        //Key , valor

        dadoDoObjeto.put(TypeDataModel.IDTYPE,obj.getIdType());
        dadoDoObjeto.put(TypeDataModel.NAMETYPE,obj.getNameType());
        dadoDoObjeto.put(TypeDataModel.URLTYPE,obj.getUrlType());

        //Enviar dados para a classe AppDataBase

        return insert(TypeDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean alterar(Type obj) {
        dadoDoObjeto= new ContentValues();

        //Key , valor
        dadoDoObjeto.put(TypeDataModel.IDTYPE,obj.getIdType());
        dadoDoObjeto.put(TypeDataModel.NAMETYPE,obj.getNameType());
        dadoDoObjeto.put(TypeDataModel.URLTYPE,obj.getUrlType());

        //Enviar dados para a classe AppDataBase

        return updateById(TypeDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean deletar(int id) {
        return deleteById(TypeDataModel.TABELA,id);
    }

    @Override
    public List<Type> listar() {
        return getAllTypes(TypeDataModel.TABELA);
    }

    public Type getByName(String name){

        return getTypeByName(TypeDataModel.TABELA,name);
    }


}
