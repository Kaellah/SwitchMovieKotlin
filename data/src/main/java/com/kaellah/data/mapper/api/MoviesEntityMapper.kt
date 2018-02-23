package com.kaellah.data.mapper.api

import com.kaellah.data.dao.movie.Movie
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.util.Mapper


object MoviesEntityMapper : Mapper<MovieEntity, Movie>() {

    override fun map(from: Movie, payload: Any?): MovieEntity = MovieEntity().apply {
        this.id = from.id
        this.adult = from.adult
        this.backdropPath = from.backdropPath
        this.originalLanguage = from.originalLanguage
        this.originalTitle = from.originalTitle
        this.overview = from.overview
        this.releaseDate = from.releaseDate
        this.posterPath = from.posterPath
        this.popularity = from.popularity
        this.title = from.title
        this.video = from.video
        this.voteAverage = from.voteAverage
        this.voteCount = from.voteCount
    }

    override fun reverseMap(to: MovieEntity, payload: Any?): Movie {
        throw notImplementedException()
    }
}