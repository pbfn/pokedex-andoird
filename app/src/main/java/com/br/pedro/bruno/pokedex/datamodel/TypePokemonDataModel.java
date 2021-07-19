package com.br.pedro.bruno.pokedex.datamodel;

public class TypePokemonDataModel {

    //NOME DA  TABELA
    public static final String TABELA = "tbTypePokemon";

    //ATRIBUTOS DA TABELA
    public static final String IDPOKEMON="idPokemon"; //integer
    public static final String IDTYPE="idType"; //text

    //QUERY PAR CRIAR A TABELA
    public static  String queryCriarTabela ="";

    //MÉTODO PARA GERAR O SCRIPT DE CRIAÇÃO DA TABELA
    public static String criarTabela(){

        queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=IDPOKEMON+" integer not null references tbpokemon(idpokemon), ";
        queryCriarTabela +=IDTYPE+" integer not null references tbtype(idtype), ";
        queryCriarTabela +=" primary key ("+IDPOKEMON+","+IDTYPE+")";
        queryCriarTabela +=")";

        /*queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=IDPOKEMON+" integer primary key, ";
        queryCriarTabela +=IDTYPE+" integer primary key";
        queryCriarTabela +=")";*/
        return queryCriarTabela;
    }

}
