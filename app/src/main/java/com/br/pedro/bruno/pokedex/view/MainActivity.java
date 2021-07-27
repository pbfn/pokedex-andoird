package com.br.pedro.bruno.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;


import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.adapter.AdapterPokemon;
import com.br.pedro.bruno.pokedex.controller.PokemonController;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.util.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    PokemonController pokemonController;
    private RecyclerView recyclerPokemon;
    AdapterPokemon adapterPokemon;
    ImageView imgFavoriteFilter;
    Toolbar toolbarMain;
    int favorite;
    SearchView searchPoke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarMain = findViewById(R.id.toolbarMain);

        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setRecyclerPokemon();

        imgFavoriteFilter = findViewById(R.id.imgFavoriteFilter);
        imgFavoriteFilter.setClickable(true);
        imgFavoriteFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorite == 0){
                    favorite =1;
                    imgFavoriteFilter.setImageResource(R.drawable.ic_baseline_favorite_black_24);
                    ((AdapterPokemon) recyclerPokemon.getAdapter()).setFilterFavorite(favorite);
                }else{
                    favorite = 0;
                    imgFavoriteFilter.setImageResource(R.drawable.ic_baseline_favorite_border_black_24);
                    ((AdapterPokemon) recyclerPokemon.getAdapter()).setFilterFavorite(favorite);
                }
            }
        });

        searchPoke = findViewById(R.id.searchPoke);
        searchPoke.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        ((AdapterPokemon) recyclerPokemon.getAdapter()).setFilterName(newText,favorite);
                        return true;
                    }
                }
        );
    }



    @Override
    protected void onResume() {
        super.onResume();
        //botao de voltar esta muito lento.
        ((AdapterPokemon) recyclerPokemon.getAdapter()).setFilterFavorite(favorite);
    }

    public void setRecyclerPokemon(){

        pokemonController = new PokemonController(getApplicationContext());
        ArrayList<Pokemon> listPokemons;

        listPokemons = (ArrayList<Pokemon>) pokemonController.listar();

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
                        //Pokemon pokemon = pokemonController.getById(position+1);
                        Pokemon pokemon = adapterPokemon.getPokemon(position);

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