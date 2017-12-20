package com.kaellah.domain.repository.movie;

import com.kaellah.domain.entity.MovieEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * @since 12/20/17
 */
public interface MovieRepository {

    Single<List<MovieEntity>> getMovies(int page);
}
