package com.br.pedro.bruno.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.controller.PokemonController;
import com.br.pedro.bruno.pokedex.controller.StatController;
import com.br.pedro.bruno.pokedex.controller.TypeController;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.model.Stat;
import com.br.pedro.bruno.pokedex.model.Type;
import com.br.pedro.bruno.pokedex.util.PokemonUtil;
import com.br.pedro.bruno.pokedex.util.StatUtil;
import com.br.pedro.bruno.pokedex.util.TypeUtil;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    TextView txtSubtitle;
    TypeController typeController;
    PokemonController pokemonController;
    StatController statController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txtSubtitle = findViewById(R.id.txtSubtitle);
        typeController = new TypeController(getApplicationContext());
        pokemonController = new PokemonController(getApplicationContext());
        statController = new StatController(getApplicationContext());
        new RequestApis().execute();

    }

    private void telaHome(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },2000);
    }

    public class RequestApis extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            List<Type> typeList =  typeController.listar();
            List<Stat> statList =  statController.listar();
            List<Pokemon> pokemonList =  pokemonController.listar();


            txtSubtitle.setText("Buscando os Tipos");
            if(typeList.size()==0){
                TypeUtil typeUtil = new TypeUtil();
                typeUtil.getType("https://pokeapi.co/api/v2/type/",getApplicationContext());
            }


            txtSubtitle.setText("Buscando os Status");
            if(statList.size()==0){
                StatUtil statUtil = new StatUtil();
                statUtil.getStat("https://pokeapi.co/api/v2/stat/",getApplicationContext());
            }

            txtSubtitle.setText("Buscando os Pokemons");
            if(pokemonList.size()==0){
                PokemonUtil pokemonUtil = new PokemonUtil();
                pokemonUtil.getPokemon("https://pokeapi.co/api/v2/pokemon/1",getApplicationContext());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            telaHome();
        }
    }
}