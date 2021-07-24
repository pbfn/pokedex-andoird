package com.br.pedro.bruno.pokedex.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.br.pedro.bruno.pokedex.datamodel.PokemonDataModel;
import com.br.pedro.bruno.pokedex.datamodel.StatDataModel;
import com.br.pedro.bruno.pokedex.datamodel.StatPokemonDataModel;
import com.br.pedro.bruno.pokedex.datamodel.TypeDataModel;
import com.br.pedro.bruno.pokedex.datamodel.TypePokemonDataModel;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.model.Stat;
import com.br.pedro.bruno.pokedex.model.StatPokemon;
import com.br.pedro.bruno.pokedex.model.Type;
import com.br.pedro.bruno.pokedex.model.TypePokemon;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "Pokedex.sqlite";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public AppDataBase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PokemonDataModel.criarTabela());
        db.execSQL(TypePokemonDataModel.criarTabela());
        db.execSQL(TypeDataModel.criarTabela());
        db.execSQL(StatDataModel.criarTabela());
        db.execSQL(StatPokemonDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public boolean insert(String tabela, ContentValues dados){
        db = getWritableDatabase();
        boolean retorno = false;

        try{
            retorno =  db.insert(tabela,null,dados) > 0;
            return retorno;
        }catch (Exception e){

        }
        return  retorno;
    }

    /**
     *Método para deletar dados no banco de dados
     * @return
     */
    public boolean deleteById(String tabela, int id){

        db = getWritableDatabase();
        boolean retorno = false;
        try{
            retorno =  db.delete(tabela,
                    "id=?",
                    new String[] {String.valueOf(id)})>0;
            return retorno;
        }catch (Exception e){

        }

        return  retorno;
    }


    public boolean updateById(String tabela, ContentValues dados){
        db = getWritableDatabase();
        boolean retorno = false;
        //regra de negócio

        try{
            retorno =  db.update(tabela,
                    dados,
                    "idPokemon=?",
                    new String[] {String.valueOf(dados.get("idPokemon"))})>0;

            return retorno;
        }catch (Exception e){

        }

        return  retorno;
    }

    //POKEMON
    public List<Pokemon> getAllPokemons(String tabela){
        db = getWritableDatabase();
        Pokemon pokemon;

        List<Pokemon> pokemons = new ArrayList<>();
        ArrayList<TypePokemon> typesPoke;
        ArrayList<StatPokemon> statsPoke;
        String sql = "SELECT * FROM "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {

                pokemon = new Pokemon();
                pokemon.setId(cursor.getInt(cursor.getColumnIndex(PokemonDataModel.IDPOKEMON)));
                pokemon.setName(cursor.getString(cursor.getColumnIndex(PokemonDataModel.NAMEPOKEMON)));
                pokemon.setUrlImage(cursor.getString(cursor.getColumnIndex(PokemonDataModel.URLIMAGEPOKEMON)));
                pokemon.setFavorite(cursor.getInt(cursor.getColumnIndex(PokemonDataModel.ISFAVORITE)));
                pokemon.setBackgroundColor(cursor.getString(cursor.getColumnIndex(PokemonDataModel.BACKGROUNDCOLOR)));

                //types do pokemon
                typesPoke = (ArrayList<TypePokemon>) getTypePokemonById("tbTypePokemon",pokemon.getId());
                ArrayList<Type> types = new ArrayList<>();
                Type type;
                for (int i = 0; i < typesPoke.size(); i++) {
                    type = getTypeById(typesPoke.get(i).getIdType());
                    types.add(type);
                }
                pokemon.setTypes(types);

                //stats do pokemon
                statsPoke = (ArrayList<StatPokemon>) getStatsPokemonById("tbStatPokemon",pokemon.getId());
                ArrayList<Stat> stats = new ArrayList<>();
                Stat stat;
                for (int i = 0; i <statsPoke.size() ; i++) {
                    stat = getStatById(statsPoke.get(i).getIdStat());
                    stats.add(stat);
                }
                pokemon.setStats(stats);

                pokemons.add(pokemon);

            }while (cursor.moveToNext());

        }
        return pokemons;
    }

    public Pokemon getPokemonById(String tabela,int id){

        db = getWritableDatabase();
        Pokemon pokemon =  new Pokemon();;
        String sql = "SELECT * FROM "+tabela+" WHERE idPokemon="+id;
        Cursor cursor;
        cursor = db.rawQuery(sql,null);
        ArrayList<TypePokemon> typesPoke;
        ArrayList<StatPokemon> statsPoke;
        if(cursor.moveToFirst()){
            do {
                pokemon.setId(cursor.getInt(cursor.getColumnIndex(PokemonDataModel.IDPOKEMON)));
                pokemon.setName(cursor.getString(cursor.getColumnIndex(PokemonDataModel.NAMEPOKEMON)));
                pokemon.setUrlImage(cursor.getString(cursor.getColumnIndex(PokemonDataModel.URLIMAGEPOKEMON)));
                pokemon.setFavorite(cursor.getInt(cursor.getColumnIndex(PokemonDataModel.ISFAVORITE)));
                pokemon.setBackgroundColor(cursor.getString(cursor.getColumnIndex(PokemonDataModel.BACKGROUNDCOLOR)));
            }while (cursor.moveToNext());
        }

        typesPoke = (ArrayList<TypePokemon>) getTypePokemonById("tbTypePokemon",pokemon.getId());
        ArrayList<Type> types = new ArrayList<>();
        Type type;
        for (int i = 0; i < typesPoke.size(); i++) {
            type = getTypeById(typesPoke.get(i).getIdType());
            types.add(type);
        }
        pokemon.setTypes(types);

        //stats do pokemon
        statsPoke = (ArrayList<StatPokemon>) getStatsPokemonById("tbStatPokemon",pokemon.getId());
        ArrayList<Stat> stats = new ArrayList<>();
        Stat stat;
        for (int i = 0; i <statsPoke.size() ; i++) {
            stat = getStatById(statsPoke.get(i).getIdStat());
            stats.add(stat);
        }
        pokemon.setStats(stats);

        return pokemon;
    }

    //TYPES DO POKEMON
    public List<TypePokemon> getAllTypesPokemon(String tabela){
        db = getWritableDatabase();
        TypePokemon typePokemon;

        List<TypePokemon> typePokemons = new ArrayList<>();
        String sql = "SELECT * FROM "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {
                typePokemon = new TypePokemon();
                typePokemon.setIdPokemon(cursor.getInt(cursor.getColumnIndex(TypePokemonDataModel.IDPOKEMON)));
                typePokemon.setIdType(cursor.getInt(cursor.getColumnIndex(TypePokemonDataModel.IDTYPE)));
                typePokemon.setOrder(cursor.getInt(cursor.getColumnIndex(TypePokemonDataModel.ORDER)));
                typePokemons.add(typePokemon);

            }while (cursor.moveToNext());

        }
        return typePokemons;
    }

    public List<TypePokemon> getTypePokemonById(String tabela,int idPokemon){
        db = getWritableDatabase();
        TypePokemon typePokemon;

        List<TypePokemon> typePokemons = new ArrayList<>();
        String sql = "SELECT * FROM "+tabela+ " WHERE idpokemon = "+idPokemon+" order by ordem";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {
                typePokemon = new TypePokemon();
                typePokemon.setIdPokemon(cursor.getInt(cursor.getColumnIndex(TypePokemonDataModel.IDPOKEMON)));
                typePokemon.setIdType(cursor.getInt(cursor.getColumnIndex(TypePokemonDataModel.IDTYPE)));
                typePokemon.setOrder(cursor.getInt(cursor.getColumnIndex(TypePokemonDataModel.ORDER)));
                typePokemons.add(typePokemon);

            }while (cursor.moveToNext());

        }
        return typePokemons;
    }

    //STATS DO POKEMON
    public List<StatPokemon> getAllStatsPokemon(String tabela){
        db = getWritableDatabase();
        StatPokemon statPokemon;

        List<StatPokemon> statPokemons = new ArrayList<>();
        String sql = "SELECT * FROM "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {
                statPokemon = new StatPokemon();
                statPokemon.setIdPokemon(cursor.getInt(cursor.getColumnIndex(StatPokemonDataModel.IDPOKEMON)));
                statPokemon.setIdStat(cursor.getInt(cursor.getColumnIndex(StatPokemonDataModel.IDSTAT)));
                statPokemon.setBaseStat(cursor.getInt(cursor.getColumnIndex(StatPokemonDataModel.BASESTAT)));
                statPokemons.add(statPokemon);

            }while (cursor.moveToNext());

        }
        return statPokemons;
    }

    public List<StatPokemon> getStatsPokemonById(String tabela,int idPokemon){
        db = getWritableDatabase();
        StatPokemon statPokemon;

        List<StatPokemon> statPokemons = new ArrayList<>();
        String sql = "SELECT * FROM "+tabela+ " WHERE idpokemon = "+idPokemon;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {
                statPokemon = new StatPokemon();
                statPokemon.setIdPokemon(cursor.getInt(cursor.getColumnIndex(StatPokemonDataModel.IDPOKEMON)));
                statPokemon.setIdStat(cursor.getInt(cursor.getColumnIndex(StatPokemonDataModel.IDSTAT)));
                statPokemon.setBaseStat(cursor.getInt(cursor.getColumnIndex(StatPokemonDataModel.BASESTAT)));
                statPokemons.add(statPokemon);

            }while (cursor.moveToNext());

        }
        return statPokemons;
    }


    //TYPE
    public List<Type> getAllTypes(String tabela){
        db = getWritableDatabase();
        Type type;

        List<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {

                type = new Type();
                type.setIdType(cursor.getInt(cursor.getColumnIndex(TypeDataModel.IDTYPE)));
                type.setNameType(cursor.getString(cursor.getColumnIndex(TypeDataModel.NAMETYPE)));
                type.setUrlType(cursor.getString(cursor.getColumnIndex(TypeDataModel.URLTYPE)));
                types.add(type);

            }while (cursor.moveToNext());

        }
        return types;
    }

    public Type getTypeByName(String tabela,String name){

        db = getWritableDatabase();
        Type type =  new Type();;
        String sql = "SELECT * FROM "+tabela+" WHERE nameType='"+name+"'";
        Cursor cursor;
        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                type.setIdType(cursor.getInt(cursor.getColumnIndex(TypeDataModel.IDTYPE)));
                type.setNameType(cursor.getString(cursor.getColumnIndex(TypeDataModel.NAMETYPE)));
                type.setUrlType(cursor.getString(cursor.getColumnIndex(TypeDataModel.URLTYPE)));
            }while (cursor.moveToNext());
        }

        return type;
    }

    public Type getTypeById(int id){

        db = getWritableDatabase();
        Type type =  new Type();;
        String sql = "SELECT * FROM tbType WHERE idType="+id+"";
        Cursor cursor;
        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                type.setIdType(cursor.getInt(cursor.getColumnIndex(TypeDataModel.IDTYPE)));
                type.setNameType(cursor.getString(cursor.getColumnIndex(TypeDataModel.NAMETYPE)));
                type.setUrlType(cursor.getString(cursor.getColumnIndex(TypeDataModel.URLTYPE)));
            }while (cursor.moveToNext());
        }

        return type;
    }

    //STAT
    public List<Stat> getAllStats(String tabela){
        db = getWritableDatabase();
        Stat stat;

        List<Stat> stats = new ArrayList<>();
        String sql = "SELECT * FROM "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {

                stat = new Stat();
                stat.setIdStat(cursor.getInt(cursor.getColumnIndex(StatDataModel.IDSTAT)));
                stat.setNameStat(cursor.getString(cursor.getColumnIndex(StatDataModel.NAMESTAT)));
                stat.setUrlStat(cursor.getString(cursor.getColumnIndex(StatDataModel.URLSTAT)));
                stats.add(stat);

            }while (cursor.moveToNext());

        }
        return stats;
    }

    public Stat getStatByName(String tabela,String name){

        db = getWritableDatabase();
        Stat stat =  new Stat();;
        String sql = "SELECT * FROM "+tabela+" WHERE nameStat='"+name+"'";
        Cursor cursor;
        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                stat.setIdStat(cursor.getInt(cursor.getColumnIndex(StatDataModel.IDSTAT)));
                stat.setNameStat(cursor.getString(cursor.getColumnIndex(StatDataModel.NAMESTAT)));
                stat.setUrlStat(cursor.getString(cursor.getColumnIndex(StatDataModel.URLSTAT)));
            }while (cursor.moveToNext());
        }

        return stat;
    }

    public Stat getStatById(int id){

        db = getWritableDatabase();
        Stat stat =  new Stat();;
        String sql = "SELECT * FROM tbStat WHERE idStat="+id+"";
        Cursor cursor;
        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                stat.setIdStat(cursor.getInt(cursor.getColumnIndex(StatDataModel.IDSTAT)));
                stat.setNameStat(cursor.getString(cursor.getColumnIndex(StatDataModel.NAMESTAT)));
                stat.setUrlStat(cursor.getString(cursor.getColumnIndex(StatDataModel.URLSTAT)));
            }while (cursor.moveToNext());
        }

        return stat;
    }
}
