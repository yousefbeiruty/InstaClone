package com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.repository;

import androidx.lifecycle.LiveData;

import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.db.PokemonDao;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.model.Pokemon;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.model.PokemonResponse;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.network.PokemonApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

   private PokemonApiService pokemonApiService;
   private PokemonDao pokemonDao;

    @Inject
    public Repository(PokemonApiService pokemonApiService,PokemonDao pokemonDao) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonDao=pokemonDao;
    }

    public Observable<PokemonResponse> getPokemons(){
          return pokemonApiService.getPokemon();
    }

    public void insertPokemon(Pokemon pokemon){
        pokemonDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String name){
        pokemonDao.deletePokemon(name);
    }

    public LiveData<List<Pokemon>>getFavoratePokemons(){
        return pokemonDao.getPokemons();
    }

}
