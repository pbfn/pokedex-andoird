package com.br.pedro.bruno.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.controller.PokemonController;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DadosPokemon extends AppCompatActivity {

    ImageView imgPokemonDados;
    TextView txtNomePokemon,txtStat1Pokemon,txtStat2Pokemon;
    PokemonController pokemonController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pokemon);

        imgPokemonDados = findViewById(R.id.imgPokemonDados);
        txtNomePokemon = findViewById(R.id.txtNomePokemon);
        txtStat1Pokemon = findViewById(R.id.txtStat1Pokemon);
        txtStat2Pokemon = findViewById(R.id.txtStat2Pokemon);

        pokemonController = new PokemonController(getApplicationContext());
        Pokemon pokemon = pokemon = pokemonController.getById(getIntent().getIntExtra("pokemonSelecionado",1));


        Picasso.get().load(pokemon.getUrlImage()).into(imgPokemonDados);
        txtNomePokemon.setText(pokemon.getName());

        txtStat1Pokemon.setText(pokemon.getTypes().get(0).getNameType());
        if(pokemon.getTypes().size()>1){
            txtStat2Pokemon.setText(pokemon.getTypes().get(1).getNameType());
        }else{
            txtStat2Pokemon.setText("");
        }
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(pokemon.getBackgroundColor()));


    }


}