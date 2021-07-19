package com.br.pedro.bruno.pokedex.datamodel;

public class StatDataModel {

    //NOME DA  TABELA
    public static final String TABELA = "tbStat";

    //ATRIBUTOS DA TABELA
    public static final String IDSTAT="idStat"; //integer
    public static final String NAMESTAT="nameStat"; //text
    public static final String URLSTAT="urlStat"; //text

    //QUERY PAR CRIAR A TABELA
    public static  String queryCriarTabela ="";

    //MÉTODO PARA GERAR O SCRIPT DE CRIAÇÃO DA TABELA
    public static String criarTabela(){

        queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=IDSTAT+" integer primary key, ";
        queryCriarTabela +=URLSTAT+" text, ";
        queryCriarTabela +=NAMESTAT+" text";
        queryCriarTabela +=")";

        return queryCriarTabela;
    }

}
