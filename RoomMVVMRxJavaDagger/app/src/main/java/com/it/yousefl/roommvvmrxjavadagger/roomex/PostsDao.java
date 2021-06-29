package com.it.yousefl.roommvvmrxjavadagger.roomex;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.it.yousefl.roommvvmrxjavadagger.roomex.Post;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PostsDao {

    @Insert
    Completable insertPost(Post post);

    @Query("SELECT * FROM posts_table")
    Single<List<Post>> getPosts();
}
