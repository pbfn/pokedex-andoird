package com.br.pedro.bruno.pokedex.util;

import android.content.Context;

import com.br.pedro.bruno.pokedex.controller.StatController;
import com.br.pedro.bruno.pokedex.model.Stat;
import com.br.pedro.bruno.pokedex.model.Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StatUtil {

    ArrayList<Stat> stats = new ArrayList<>();
    StatController statController;

    public ArrayList<Stat> getStat(String url, Context context){
        String json;
        ArrayList<Stat> retorno;
        json = ApiUtil.getDados(url);
        statController = new StatController(context);
        retorno = parseStatJson(json);
        return retorno;
    }


    public ArrayList<Stat> parseStatJson(String json){


        try {
            JSONObject objectJson = new JSONObject(json);
            JSONArray arrayType = objectJson.getJSONArray("results");

            for (int i = 0; i < arrayType.length(); i++) {
                JSONObject jsonObject = arrayType.getJSONObject(i);

                Stat stat = new Stat();

                stat.setIdStat(i+1);
                String name = jsonObject.getString("name");
                //Primeira letra maiúscula
                name = name.substring(0,1).toUpperCase().concat(name.substring(1));
                stat.setNameStat(name);
                stat.setUrlStat(jsonObject.getString("url"));
                stats.add(stat);
                 statController.incluir(stat);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stats;
    }
}
