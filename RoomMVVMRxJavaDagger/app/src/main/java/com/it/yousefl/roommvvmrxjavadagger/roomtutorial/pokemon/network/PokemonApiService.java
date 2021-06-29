package com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.network;

import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.model.PokemonResponse;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiService {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemon();
}
