package com.br.pedro.bruno.pokedex.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.controller.PokemonController;
import com.br.pedro.bruno.pokedex.controller.StatPokemonController;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.model.StatPokemon;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class DadosPokemon extends AppCompatActivity {
    SimpleDraweeView imgPokemonDados;
    ImageView imgFavPoke;
    TextView txtNomePokemon,txtStat1Pokemon,txtStat2Pokemon;
    TextView txtStat1,txtStat2,txtStat3,txtStat4,txtStat5,txtStat6;
    TextView txtVlStat1,txtVlStat2,txtVlStat3,txtVlStat4,txtVlStat5,txtVlStat6;
    Toolbar toolbar;

    ProgressBar progressBarStat1,progressBarStat2,progressBarStat3,progressBarStat4,progressBarStat5,progressBarStat6;

    StatPokemonController statPokemonController;
    PokemonController pokemonController;
    ArrayList<StatPokemon> statPokemonArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_dados_pokemon);
        initComponents();
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        Pokemon pokemon  = (Pokemon) getIntent().getSerializableExtra("pokemonSelecionado");
        toolbar.setTitle(pokemon.getName());

        //Picasso.get().load(pokemon.getUrlImage()).into(imgPokemonDados);

        Uri uri = Uri.parse(pokemon.getUrlImage());
        imgPokemonDados.setImageURI(uri);
        txtNomePokemon.setText(pokemon.getName());

        txtStat1Pokemon.setText(pokemon.getTypes().get(0).getNameType());

        if(pokemon.getTypes().size()>1){
            txtStat2Pokemon.setText(pokemon.getTypes().get(1).getNameType());
        }else{
            txtStat2Pokemon.setVisibility(View.GONE);
            txtStat1Pokemon.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
            txtStat1Pokemon.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        View view = this.getWindow().getDecorView();

        view.setBackgroundColor(Color.parseColor(pokemon.getBackgroundColor()));
        toolbar.setBackgroundColor(Color.parseColor(pokemon.getBackgroundColor()));

        txtStat1.setText(pokemon.getStats().get(0).getNameStat());
        txtStat2.setText(pokemon.getStats().get(1).getNameStat());
        txtStat3.setText(pokemon.getStats().get(2).getNameStat());
        txtStat4.setText(pokemon.getStats().get(3).getNameStat());
        txtStat5.setText(pokemon.getStats().get(4).getNameStat());
        txtStat6.setText(pokemon.getStats().get(5).getNameStat());


        statPokemonArrayList = (ArrayList<StatPokemon>) statPokemonController.getByIdPokemon(pokemon.getId());

        txtVlStat1.setText(Integer.toString(statPokemonArrayList.get(0).getBaseStat()));
        txtVlStat2.setText(Integer.toString(statPokemonArrayList.get(1).getBaseStat()));
        txtVlStat3.setText(Integer.toString(statPokemonArrayList.get(2).getBaseStat()));
        txtVlStat4.setText(Integer.toString(statPokemonArrayList.get(3).getBaseStat()));
        txtVlStat5.setText(Integer.toString(statPokemonArrayList.get(4).getBaseStat()));
        txtVlStat6.setText(Integer.toString(statPokemonArrayList.get(5).getBaseStat()));


        imgFavPoke.setClickable(true);
        imgFavPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pokemon.getIsFavorite() == 0){
                    imgFavPoke.setImageResource(R.drawable.ic_baseline_favorite_24);
                    pokemon.setFavorite(1);
                    pokemonController.alterar(pokemon);

                }else{
                    imgFavPoke.setImageResource(R.drawable.ic_outline_favorite_border_24);
                    pokemon.setFavorite(0);
                    pokemonController.alterar(pokemon);
                }
            }
        });

        if(pokemon.getIsFavorite() == 0){
            imgFavPoke.setImageResource(R.drawable.ic_outline_favorite_border_24);
        }else{
            imgFavPoke.setImageResource(R.drawable.ic_baseline_favorite_24);
        }


        progressBarStat1.setProgress(statPokemonArrayList.get(0).getBaseStat());
        if(statPokemonArrayList.get(0).getBaseStat()<50){
            progressBarStat1.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statBad), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(0).getBaseStat()>=50 && statPokemonArrayList.get(0).getBaseStat()<70){
            progressBarStat1.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statAverage), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(0).getBaseStat()>=70){
            progressBarStat1.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statGood), PorterDuff.Mode.SRC_IN);
        }

        progressBarStat2.setProgress(statPokemonArrayList.get(1).getBaseStat());
        if(statPokemonArrayList.get(1).getBaseStat()<50){
            progressBarStat2.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statBad), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(1).getBaseStat()>=50 && statPokemonArrayList.get(1).getBaseStat()<70){
            progressBarStat2.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statAverage), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(1).getBaseStat()>=70){
            progressBarStat2.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statGood), PorterDuff.Mode.SRC_IN);
        }

        progressBarStat3.setProgress(statPokemonArrayList.get(2).getBaseStat());
        if(statPokemonArrayList.get(2).getBaseStat()<50){
            progressBarStat3.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statBad), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(2).getBaseStat()>=50 && statPokemonArrayList.get(2).getBaseStat()<70){
            progressBarStat3.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statAverage), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(2).getBaseStat()>=70){
            progressBarStat3.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statGood), PorterDuff.Mode.SRC_IN);
        }

        progressBarStat4.setProgress(statPokemonArrayList.get(3).getBaseStat());
        if(statPokemonArrayList.get(3).getBaseStat()<50){
            progressBarStat4.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statBad), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(3).getBaseStat()>=50 && statPokemonArrayList.get(3).getBaseStat()<70){
            progressBarStat4.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statAverage), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(3).getBaseStat()>=70){
            progressBarStat4.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statGood), PorterDuff.Mode.SRC_IN);
        }

        progressBarStat5.setProgress(statPokemonArrayList.get(4).getBaseStat());
        if(statPokemonArrayList.get(4).getBaseStat()<50){
            progressBarStat5.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statBad), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(4).getBaseStat()>=50 && statPokemonArrayList.get(4).getBaseStat()<70){
            progressBarStat5.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statAverage), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(4).getBaseStat()>=70){
            progressBarStat5.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statGood), PorterDuff.Mode.SRC_IN);
        }

        progressBarStat6.setProgress(statPokemonArrayList.get(5).getBaseStat());
        if(statPokemonArrayList.get(5).getBaseStat()<50){
            progressBarStat6.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statBad), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(5).getBaseStat()>=50 && statPokemonArrayList.get(5).getBaseStat()<70){
            progressBarStat6.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statAverage), PorterDuff.Mode.SRC_IN);
        }else if(statPokemonArrayList.get(5).getBaseStat()>=70){
            progressBarStat6.getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.statGood), PorterDuff.Mode.SRC_IN);
        }

    }

    public void initComponents(){
        statPokemonController = new StatPokemonController(getApplicationContext());
        pokemonController = new PokemonController(getApplicationContext());

        toolbar = findViewById(R.id.toolbar);

        imgPokemonDados = findViewById(R.id.imgPokemonDados);
        imgFavPoke = findViewById(R.id.imgFavPoke);

        txtNomePokemon = findViewById(R.id.txtNomePokemon);
        txtStat1Pokemon = findViewById(R.id.txtStat1Pokemon);
        txtStat2Pokemon = findViewById(R.id.txtStat2Pokemon);

        txtStat1 = findViewById(R.id.txtStat1);
        txtStat2 = findViewById(R.id.txtStat2);
        txtStat3 = findViewById(R.id.txtStat3);
        txtStat4= findViewById(R.id.txtStat4);
        txtStat5 = findViewById(R.id.txtStat5);
        txtStat6 = findViewById(R.id.txtStat6);

        txtVlStat1= findViewById(R.id.txtVlStat1);
        txtVlStat2= findViewById(R.id.txtVlStat2);
        txtVlStat3= findViewById(R.id.txtVlStat3);
        txtVlStat4= findViewById(R.id.txtVlStat4);
        txtVlStat5= findViewById(R.id.txtVlStat5);
        txtVlStat6= findViewById(R.id.txtVlStat6);

        progressBarStat1 = findViewById(R.id.progressBarStat1);
        progressBarStat2 = findViewById(R.id.progressBarStat2);
        progressBarStat3 = findViewById(R.id.progressBarStat3);
        progressBarStat4 = findViewById(R.id.progressBarStat4);
        progressBarStat5 = findViewById(R.id.progressBarStat5);
        progressBarStat6 = findViewById(R.id.progressBarStat6);

        progressBarStat1.setMax(100);
        progressBarStat2.setMax(100);
        progressBarStat3.setMax(100);
        progressBarStat4.setMax(100);
        progressBarStat5.setMax(100);
        progressBarStat6.setMax(100);

    }

    public void getImage(String imageUrl) {

    }

}