package com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.model.Pokemon;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.model.PokemonResponse;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {

    private Repository repository;

    MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();
    LiveData<List<Pokemon>> favoriteList = null;

    public LiveData<List<Pokemon>> getFavoriteList() {
        return favoriteList;
    }

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {

        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(@NonNull PokemonResponse pokemonResponse) throws Exception {
                        ArrayList<Pokemon> arrayList = pokemonResponse.getResults();
                        for (Pokemon pokemon : arrayList) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return arrayList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error -> Log.e("ViewModel", error.getMessage()));

    }

    public void insertPokemon(Pokemon pokemon){
        repository.insertPokemon(pokemon);
    }

    public void deletePokemon(String name){
        repository.deletePokemon(name);
    }

    public void getFavPokemon(){
      favoriteList=repository.getFavoratePokemons();
    }
}
