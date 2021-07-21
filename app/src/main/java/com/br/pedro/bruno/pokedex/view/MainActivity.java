package com.br.pedro.bruno.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;


import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.adapter.AdapterPokemon;
import com.br.pedro.bruno.pokedex.controller.PokemonController;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.util.RecyclerClickListener;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    PokemonController pokemonController;
   private RecyclerView recyclerPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerPokemon();




    }

    public void setRecyclerPokemon(){

        pokemonController = new PokemonController(getApplicationContext());
        ArrayList<Pokemon> listPokemons = (ArrayList<Pokemon>) pokemonController.listar();



        recyclerPokemon = findViewById(R.id.recyclerPokemon);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPokemon.setLayoutManager(layoutManager);

        //recyclerPokemon.setHasFixedSize(true);
        AdapterPokemon adapterPokemon = new AdapterPokemon(listPokemons);
        recyclerPokemon.setAdapter(adapterPokemon);

        recyclerPokemon.addOnItemTouchListener(new RecyclerClickListener(
                getApplicationContext(),
                recyclerPokemon,
                new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),DadosPokemon.class);

                        intent.putExtra("pokemonSelecionado",position+1);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));


    }


}