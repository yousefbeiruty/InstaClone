package com.it.yousefl.roommvvmrxjavadagger.roomex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.it.yousefl.roommvvmrxjavadagger.R;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView postsRecyclerView;
    private Button insertBtn, getBtn;
    private EditText titleEt, bodyEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBtn = findViewById(R.id.insertButton);
        getBtn = findViewById(R.id.getButton);

        titleEt = findViewById(R.id.editTexttitle);
        bodyEt = findViewById(R.id.editTextBody);

        postsRecyclerView = findViewById(R.id.posts_recyclerView);
        final PostsAdapte adapter = new PostsAdapte();
        postsRecyclerView.setAdapter(adapter);

        final PostsDataBase postsDatabase = PostsDataBase.getInstance(this);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postsDatabase.postsDao().insertPost(new Post(new User(2,titleEt.getText().toString()), titleEt.getEditableText().toString(), bodyEt.getEditableText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });


        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postsDatabase.postsDao().getPosts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Post> posts) {
                                adapter.setList(posts);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });

    }
}