package com.br.pedro.bruno.pokedex.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
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
          String codigo = Integer.toString(pokemon.getId());
          switch (codigo.length()) {
              case 1:
                  holder.txtIdPokemon.setText("#00"+codigo);
                  break;
              case 2:
                  holder.txtIdPokemon.setText("#0"+codigo);
                  break;
              case 3:
                  holder.txtIdPokemon.setText("#"+codigo);
                  break;
          }
          holder.txtNamePokemon.setText(pokemon.getName());
          holder.cardPokemon.setCardBackgroundColor(Color.parseColor(pokemon.getBackgroundColor()));

        holder.txtStat1.setText(pokemon.getTypes().get(0).getNameType());
        if(pokemon.getTypes().size()>1){
            holder.txtStat2.setText(pokemon.getTypes().get(1).getNameType());
        }else{
            holder.txtStat2.setVisibility(View.INVISIBLE);
        }
        Picasso.get().load(pokemon.getUrlImage()).into(holder.imgPokemon);

        if(pokemon.getIsFavorite() == 0){
            holder.imgFavorite.setImageResource(R.drawable.ic_outline_favorite_border_24);
        }else{
            holder.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
    }



    @Override
    public int getItemCount() {
        return listaPokemons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtIdPokemon,txtNamePokemon,txtStat1,txtStat2;
        ImageView imgPokemon,imgFavorite;
        CardView cardPokemon;

        public MyViewHolder(View view) {
            super(view);
            txtIdPokemon = view.findViewById(R.id.txtIdPokemon);
            txtNamePokemon = view.findViewById(R.id.txtNamePokemon);
            txtStat1 = view.findViewById(R.id.txtStat1);
            txtStat2 = view.findViewById(R.id.txtStat2);
            imgPokemon = view.findViewById(R.id.imgPokemon);
            imgFavorite= view.findViewById(R.id.imgFavorite);
            cardPokemon = view.findViewById(R.id.cardPokemon);
        }

    }

}


