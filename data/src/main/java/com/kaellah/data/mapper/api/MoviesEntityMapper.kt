package com.kaellah.data.mapper.api

import com.kaellah.data.dao.movie.Movie
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.util.Mapper


object MoviesEntityMapper : Mapper<MovieEntity, Movie>() {

    override fun map(from: Movie, payload: Any?): MovieEntity {
        val movie = MovieEntity()
        movie.id = from.id
        movie.adult = from.adult
        movie.backdropPath = from.backdropPath
        movie.originalLanguage = from.originalLanguage
        movie.originalTitle = from.originalTitle
        movie.overview = from.overview
        movie.releaseDate = from.releaseDate
        movie.posterPath = from.posterPath
        movie.popularity = from.popularity
        movie.title = from.title
        movie.video = from.video
        movie.voteAverage = from.voteAverage
        movie.voteCount = from.voteCount
        return movie
    }

    override fun reverseMap(to: MovieEntity, payload: Any?): Movie {
        throw notImplementedException()
    }
}