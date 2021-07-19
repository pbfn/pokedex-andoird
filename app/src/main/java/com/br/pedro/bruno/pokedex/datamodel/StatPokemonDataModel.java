package com.br.pedro.bruno.pokedex.datamodel;

public class StatPokemonDataModel {

    //NOME DA  TABELA
    public static final String TABELA = "tbStatPokemon";

    //ATRIBUTOS DA TABELA
    public static final String IDPOKEMON="idPokemon"; //integer
    public static final String IDSTAT="idStat"; //text
    public static final String BASESTAT="baseStat"; //text

    //QUERY PAR CRIAR A TABELA
    public static  String queryCriarTabela ="";

    //MÉTODO PARA GERAR O SCRIPT DE CRIAÇÃO DA TABELA
    public static String criarTabela(){

        queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=IDPOKEMON+" integer not null references tbpokemon(idpokemon), ";
        queryCriarTabela +=IDSTAT+" integer not null references tbstat(idstat), ";
        queryCriarTabela +=BASESTAT+" integer, ";
        queryCriarTabela +=" primary key ("+IDPOKEMON+","+IDSTAT+")";
        queryCriarTabela +=")";

        return queryCriarTabela;
    }

}
