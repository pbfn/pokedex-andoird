package com.br.pedro.bruno.pokedex.util;

import android.content.Context;
import android.graphics.Color;

import com.br.pedro.bruno.pokedex.controller.PokemonController;
import com.br.pedro.bruno.pokedex.controller.StatController;
import com.br.pedro.bruno.pokedex.controller.StatPokemonController;
import com.br.pedro.bruno.pokedex.controller.TypeController;
import com.br.pedro.bruno.pokedex.controller.TypePokemonController;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.model.Stat;
import com.br.pedro.bruno.pokedex.model.StatPokemon;
import com.br.pedro.bruno.pokedex.model.Type;
import com.br.pedro.bruno.pokedex.model.TypePokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PokemonUtil {

    Pokemon pokemon = new Pokemon();
    TypeController typeController;
    PokemonController pokemonController;
    TypePokemonController typePokemonController;
    StatPokemonController statPokemonController;
    StatController statController;

    public Pokemon getPokemon(String url, Context context){
        String json;
        Pokemon retorno;
        json = ApiUtil.getDados(url);
        typeController = new TypeController(context);
        pokemonController = new PokemonController(context);
        typePokemonController = new TypePokemonController(context);
        statController = new StatController(context);
        statPokemonController = new StatPokemonController(context);
        retorno = parsePokmeonJson(json);
        return retorno;
    }

    public Pokemon parsePokmeonJson(String json){

        try {

            JSONObject jsonPokemon = new JSONObject(json);

            int id = jsonPokemon.getInt("id");
            pokemon.setId(id);
            pokemon.setName(jsonPokemon.getString("name"));


            JSONArray arrayTypes = jsonPokemon.getJSONArray("types");
            ArrayList<Type> typeArrayList = new ArrayList<>();

            TypePokemon typePokemon = new TypePokemon();
            typePokemon.setIdPokemon(pokemon.getId());

            //pegando o tipos do pokemon
            for (int i = 0; i <arrayTypes.length() ; i++) {
                JSONObject type = arrayTypes.getJSONObject(i);
                JSONObject typeJSONObject = type.getJSONObject("type");
                String typeString = typeJSONObject.getString("name");

                Type typePoke =  typeController.getByName(typeString);
                typeArrayList.add(typePoke);
                typePokemon.setIdType(typePoke.getIdType());
                typePokemon.setOrder(i+1);
                typePokemonController.incluir(typePokemon);
            }
            pokemon.setTypes(typeArrayList);
            pokemon.setBackgroundColor(selectBackgroundColor(typeArrayList.get(0).getNameType()));

            JSONArray arrayStats = jsonPokemon.getJSONArray("stats");
            ArrayList<Stat> statArrayList = new ArrayList<>();

            StatPokemon statPokemon = new StatPokemon();
            statPokemon.setIdPokemon(pokemon.getId());


            for (int i = 0; i < arrayStats.length(); i++) {
                JSONObject statsJSONObject = arrayStats.getJSONObject(i);
                statPokemon.setBaseStat(statsJSONObject.getInt("base_stat"));
                JSONObject statJsonObject = statsJSONObject.getJSONObject("stat");
                String statString = statJsonObject.getString("name");
                Stat statPoke = statController.getByName(statString);
                statArrayList.add(statPoke);
                statPokemon.setIdStat(statPoke.getIdStat());
                statPokemonController.incluir(statPokemon);
            }

            pokemon.setStats(statArrayList);
            pokemon.setUrlImage("https:pokeres.bastionbot.org/images/pokemon/"+id+".png");

        }catch (JSONException e) {
            e.printStackTrace();
        }

        pokemonController.incluir(pokemon);
        return pokemon;
    }

    private String selectBackgroundColor(String nameType){
        switch (nameType){
            case "normal":
               return "#FB6C6C";
            case "fighting":
                return "#ff5500";
            case "flying":
                return "#FB6C6C";
            case "poison":
                return "#CC00ff";
            case "ground":
                return "#802b00";
            case "rock":
                return "#";
            case "bug":
                return "#cc9900";
            case "ghost":
                return "#";
            case "steel":
                return "#FB6C6C";
            case "fire":
                return "#FB6C6C";
            case "water":
                return "#1AA3FF";
            case "grass":
                return "#48D0B0";
            case "electric":
                return "#ffff66";
            case "psychic":
                return "#FB6C6C";
            case "ice":
                return "#99ccff";
            case "dragon":
                return "#FB6C6C";
            case "dark":
                return "#FB6C6C";
            case "fairy":
                return "#FB6C6C";
            case "unknown":
                return "#FB6C6C";
            case "shadow":
                return "#FB6C6C";
        }
        return "#FFFFFF";
    }
}
