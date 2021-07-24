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
    AdapterPokemon adapterPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerPokemon();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //botao de voltar esta muito lento.
        adapterPokemon.notifyDataSetChanged();
    }

    public void setRecyclerPokemon(){

        pokemonController = new PokemonController(getApplicationContext());
        ArrayList<Pokemon> listPokemons = (ArrayList<Pokemon>) pokemonController.listar();



        recyclerPokemon = findViewById(R.id.recyclerPokemon);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPokemon.setLayoutManager(layoutManager);

        recyclerPokemon.setHasFixedSize(true);
        recyclerPokemon.setItemViewCacheSize(10);
        recyclerPokemon.setDrawingCacheEnabled(true);


        adapterPokemon = new AdapterPokemon(listPokemons);


        recyclerPokemon.setAdapter(adapterPokemon);

        recyclerPokemon.addOnItemTouchListener(new RecyclerClickListener(
                getApplicationContext(),
                recyclerPokemon,
                new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Pokemon pokemon = pokemonController.getById(position+1);

                        Intent intent = new Intent(getApplicationContext(),DadosPokemon.class);
                        intent.putExtra("pokemonSelecionado",pokemon);
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