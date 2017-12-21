package com.kaellah.data.mapper.database

import com.kaellah.data.dao.movie.Movie
import com.kaellah.data.response.MovieResponse
import com.kaellah.domain.util.Mapper

object MoviesDaoMapper : Mapper<Movie, MovieResponse>() {

    override fun map(from: MovieResponse, payload: Any?): Movie = from.let {
        Movie(it.id, it.adult, it.backdropPath, it.originalLanguage, it.originalTitle, it.overview, it.releaseDate,
              it.posterPath, it.popularity, it.title, it.video, it.voteAverage, it.voteCount)
    }

    override fun reverseMap(to: Movie, payload: Any?): MovieResponse {
        throw notImplementedException()
    }
}