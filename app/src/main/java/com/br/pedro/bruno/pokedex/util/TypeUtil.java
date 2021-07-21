package com.br.pedro.bruno.pokedex.util;

import android.content.Context;

import com.br.pedro.bruno.pokedex.controller.TypeController;
import com.br.pedro.bruno.pokedex.model.Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TypeUtil {

    ArrayList<Type> types = new ArrayList<>();

    TypeController typeController;

    public ArrayList<Type> getType(String url,Context context){
        String json;
        ArrayList<Type> retorno;
        json = ApiUtil.getDados(url);
        typeController= new TypeController(context);
        retorno = parseTypeJson(json);
        return retorno;
    }


    public ArrayList<Type> parseTypeJson(String json){

        try {
            JSONObject objectJson = new JSONObject(json);
            JSONArray arrayType = objectJson.getJSONArray("results");

            for (int i = 0; i < arrayType.length(); i++) {
                JSONObject jsonObject = arrayType.getJSONObject(i);

                Type type = new Type();

                type.setIdType(i+1);
                String name = jsonObject.getString("name");
                //Primeira letra maiúscula
                name = name.substring(0,1).toUpperCase().concat(name.substring(1));
                type.setNameType(name);
                type.setUrlType(jsonObject.getString("url"));
                types.add(type);
                typeController.incluir(type);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return types;

    }
}
