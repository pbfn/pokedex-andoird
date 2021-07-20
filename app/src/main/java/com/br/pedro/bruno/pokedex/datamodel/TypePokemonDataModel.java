package com.br.pedro.bruno.pokedex.datamodel;

public class TypePokemonDataModel {

    //NOME DA  TABELA
    public static final String TABELA = "tbTypePokemon";

    //ATRIBUTOS DA TABELA
    public static final String IDPOKEMON="idPokemon";
    public static final String IDTYPE="idType";
    public static final String ORDER="ordem";

    //QUERY PAR CRIAR A TABELA
    public static  String queryCriarTabela ="";

    //MÉTODO PARA GERAR O SCRIPT DE CRIAÇÃO DA TABELA
    public static String criarTabela(){

        queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=IDPOKEMON+" integer not null references tbpokemon(idpokemon), ";
        queryCriarTabela +=ORDER+" integer not null, ";
        queryCriarTabela +=IDTYPE+" integer not null references tbtype(idtype), ";
        queryCriarTabela +=" primary key ("+IDPOKEMON+","+IDTYPE+")";
        queryCriarTabela +=")";

        return queryCriarTabela;
    }

}
