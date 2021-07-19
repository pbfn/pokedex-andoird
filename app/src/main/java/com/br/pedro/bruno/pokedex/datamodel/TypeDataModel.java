package com.br.pedro.bruno.pokedex.datamodel;

public class TypeDataModel {

    //NOME DA  TABELA
    public static final String TABELA = "tbType";

    //ATRIBUTOS DA TABELA
    public static final String IDTYPE="idType"; //integer
    public static final String NAMETYPE="nameType"; //text
    public static final String URLTYPE="urlType"; //text

    //QUERY PAR CRIAR A TABELA
    public static  String queryCriarTabela ="";

    //MÉTODO PARA GERAR O SCRIPT DE CRIAÇÃO DA TABELA
    public static String criarTabela(){

        queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=IDTYPE+" integer primary key, ";
        queryCriarTabela +=URLTYPE+" text, ";
        queryCriarTabela +=NAMETYPE+" text";
        queryCriarTabela +=")";

        return queryCriarTabela;
    }

}
