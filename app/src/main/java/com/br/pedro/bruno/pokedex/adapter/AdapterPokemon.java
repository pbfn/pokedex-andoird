package com.br.pedro.bruno.pokedex.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.br.pedro.bruno.pokedex.R;
import com.br.pedro.bruno.pokedex.model.Pokemon;
import com.br.pedro.bruno.pokedex.model.Type;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.MyViewHolder> {


    ArrayList<Pokemon> listaPokemons;

    public AdapterPokemon(ArrayList<Pokemon> pokemons){
        this.listaPokemons = pokemons;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View pokemonLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pokemon,parent,false);
        return new MyViewHolder(pokemonLista);
    }

    @Override
    public void onBindViewHolder(AdapterPokemon.MyViewHolder holder, int position) {
          Pokemon pokemon = listaPokemons.get(position);
          holder.txtIdPokemon.setText(Integer.toString(pokemon.getId()));
          holder.txtNamePokemon.setText(pokemon.getName());

          ArrayList<Type> types = pokemon.getTypes();

          switch (types.get(0).getNameType()){
              case "normal":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "fighting":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#ff5500"));break;
              case "flying":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "poison":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#CC00ff"));break;
              case "ground":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#802b00"));break;
              case "rock":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#"));break;
              case "bug":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#cc9900"));break;
              case "ghost":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#"));break;
              case "steel":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "fire":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "water":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#1AA3FF"));break;
              case "grass":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#48D0B0"));break;
              case "electric":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#ffff66"));break;
              case "psychic":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "ice":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#99ccff"));break;
              case "dragon":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "dark":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "fairy":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "unknown":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
              case "shadow":
                  holder.cardPokemon.setCardBackgroundColor(Color.parseColor("#FB6C6C"));break;
          }

        Picasso.get().load(pokemon.getUrlImage()).into(holder.imgPokemon);
    }



    @Override
    public int getItemCount() {
        return listaPokemons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtIdPokemon,txtNamePokemon;
        ImageView imgPokemon;
        CardView cardPokemon;

        public MyViewHolder(View view) {
            super(view);
            txtIdPokemon = view.findViewById(R.id.txtIdPokemon);
            txtNamePokemon = view.findViewById(R.id.txtNamePokemon);
            imgPokemon = view.findViewById(R.id.imgPokemon);
            cardPokemon = view.findViewById(R.id.cardPokemon);
        }
    }

}


