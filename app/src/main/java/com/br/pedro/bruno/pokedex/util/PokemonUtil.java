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
            String name = jsonPokemon.getString("name");
            //Primeira letra maiúscula
            name = name.substring(0,1).toUpperCase().concat(name.substring(1));
            pokemon.setName(name);


            JSONArray arrayTypes = jsonPokemon.getJSONArray("types");
            ArrayList<Type> typeArrayList = new ArrayList<>();

            TypePokemon typePokemon = new TypePokemon();
            typePokemon.setIdPokemon(pokemon.getId());

            //pegando o tipos do pokemon
            for (int i = 0; i <arrayTypes.length() ; i++) {
                JSONObject type = arrayTypes.getJSONObject(i);
                JSONObject typeJSONObject = type.getJSONObject("type");
                String typeString = typeJSONObject.getString("name");
                typeString = typeString.substring(0,1).toUpperCase().concat(typeString.substring(1));
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
                statString = statString.substring(0,1).toUpperCase().concat(statString.substring(1));
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
            case "Normal":
               return "#FB6C6C";
            case "Fighting":
                return "#ff5500";
            case "Flying":
                return "#FB6C6C";
            case "Poison":
                return "#CC00ff";
            case "Ground":
                return "#802b00";
            case "Rock":
                return "#802b00";
            case "Bug":
                return "#cc9900";
            case "Ghost":
                return "#cc9900";
            case "Steel":
                return "#FB6C6C";
            case "Fire":
                return "#FB6C6C";
            case "Water":
                return "#1AA3FF";
            case "Grass":
                return "#48D0B0";
            case "Electric":
                return "#ffff66";
            case "Psychic":
                return "#FB6C6C";
            case "Ice":
                return "#99ccff";
            case "Dragon":
                return "#FB6C6C";
            case "Dark":
                return "#FB6C6C";
            case "Fairy":
                return "#FB6C6C";
            case "Unknown":
                return "#FB6C6C";
            case "Shadow":
                return "#FB6C6C";
        }
        return "#FFFFFF";
    }
}
