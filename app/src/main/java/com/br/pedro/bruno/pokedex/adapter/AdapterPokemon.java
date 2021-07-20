package com.br.pedro.bruno.pokedex.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
          holder.cardPokemon.setCardBackgroundColor(Color.parseColor(pokemon.getBackgroundColor()));
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


