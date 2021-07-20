package com.br.pedro.bruno.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.adapter.AdapterPokemon;
import com.br.pedro.bruno.pokedex.controller.PokemonController;
import com.br.pedro.bruno.pokedex.model.Pokemon;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    PokemonController pokemonController;
    RecyclerView recyclerPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         pokemonController = new PokemonController(getApplicationContext());
         ArrayList<Pokemon> listPokemons = (ArrayList<Pokemon>) pokemonController.listar();

         AdapterPokemon adapterPokemon = new AdapterPokemon(listPokemons);

         recyclerPokemon = findViewById(R.id.recyclerPokemon);
         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
         recyclerPokemon.setLayoutManager(layoutManager);
         recyclerPokemon.setHasFixedSize(true);
         recyclerPokemon.setAdapter(adapterPokemon);

    }

}