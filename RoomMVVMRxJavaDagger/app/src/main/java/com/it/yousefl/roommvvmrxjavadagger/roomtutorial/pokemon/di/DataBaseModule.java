package com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.di;

import android.app.Application;

import androidx.room.Room;

import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.db.PokemonDao;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.db.PokemonDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static PokemonDataBase providePokemonDataBase(Application application){
        return Room.databaseBuilder(application,PokemonDataBase.class,"fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokemonDao providePokemonDao(PokemonDataBase pokemonDataBase){
        return pokemonDataBase.pokemonDao();
    }
}
