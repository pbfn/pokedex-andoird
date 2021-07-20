package com.br.pedro.bruno.pokedex.datamodel;

public class PokemonDataModel {

    //NOME DA  TABELA
    public static final String TABELA = "tbPokemon";

    //ATRIBUTOS DA TABELA
    public static final String IDPOKEMON="idPokemon"; //integer
    public static final String NAMEPOKEMON="namePokemon"; //text
    public static final String URLIMAGEPOKEMON="urlImagePokemon"; //text
    public static final String BACKGROUNDCOLOR="backgroundColor"; //text

    //QUERY PAR CRIAR A TABELA
    public static  String queryCriarTabela ="";

    //MÉTODO PARA GERAR O SCRIPT DE CRIAÇÃO DA TABELA
    public static String criarTabela(){

        queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=IDPOKEMON+" integer primary key, ";
        queryCriarTabela +=URLIMAGEPOKEMON+" text, ";
        queryCriarTabela +=NAMEPOKEMON+" text, ";
        queryCriarTabela +=BACKGROUNDCOLOR+" text";
        queryCriarTabela +=")";

        return queryCriarTabela;
    }

}
