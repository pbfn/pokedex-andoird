package com.br.pedro.bruno.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DadosPokemon extends AppCompatActivity {

    ImageView imgPokemonDados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pokemon);
        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("pokemonSelecionado");
        imgPokemonDados = findViewById(R.id.imgPokemonDados);

        Picasso.get().load(pokemon.getUrlImage()).into(imgPokemonDados);

    }


}