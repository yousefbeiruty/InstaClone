package com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.model.Pokemon;

@Database(entities = Pokemon.class, version = 1,exportSchema = false)
public abstract class PokemonDataBase extends RoomDatabase {
       public abstract PokemonDao pokemonDao();

}
