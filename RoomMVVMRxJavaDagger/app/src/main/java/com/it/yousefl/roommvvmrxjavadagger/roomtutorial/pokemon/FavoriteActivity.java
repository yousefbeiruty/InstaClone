package com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.it.yousefl.roommvvmrxjavadagger.R;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.adapters.PokemonAdapter;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.model.Pokemon;
import com.it.yousefl.roommvvmrxjavadagger.roomtutorial.pokemon.viewmodels.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteActivity extends AppCompatActivity {

    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView=findViewById(R.id.rc_fav);
        button=findViewById(R.id.go_to_home);
        adapter=new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);
        setUpSwipe();




        viewModel=new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getFavPokemon();
        viewModel.getFavoriteList().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                ArrayList<Pokemon>arrayList=new ArrayList<>();
                arrayList.addAll(pokemons);
                adapter.setList(arrayList);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FavoriteActivity.this,PokemonActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setUpSwipe(){
        ItemTouchHelper.SimpleCallback callback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition=viewHolder.getAdapterPosition();
                Pokemon swipedPokemon=adapter.getPokemonAt(swipedPokemonPosition);
                viewModel.deletePokemon(swipedPokemon.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(FavoriteActivity.this, "pokemon deleted to database", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}