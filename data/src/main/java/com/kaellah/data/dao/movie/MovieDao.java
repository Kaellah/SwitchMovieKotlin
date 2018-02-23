package com.kaellah.data.dao.movie;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    String TABLE_MOVIES = "movies";

    @Query("select * from " + TABLE_MOVIES)
    Flowable<List<Movie>> getAll();

    @Query("select * from " + TABLE_MOVIES + " where movies.id in (:ids)")
    List<Movie> getMovies(List<Integer> ids);

    @Query("select * from " + TABLE_MOVIES + " where movies.id like :id limit 1")
    Single<Movie> getMovie(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> movies);
}
