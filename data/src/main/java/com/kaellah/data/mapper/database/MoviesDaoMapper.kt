package com.kaellah.data.mapper.database

import com.kaellah.data.response.MovieResponse
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.util.Mapper

// TODO need correct mapper
object MoviesDaoMapper : Mapper<MovieEntity, MovieResponse>() {

    override fun map(from: MovieResponse, payload: Any?): MovieEntity {
        val movie = MovieEntity()
        movie.id = from.id
        movie.adult = from.adult
        movie.backdropPath = from.backdropPath
        movie.genreIds = from.genreIds
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

    override fun reverseMap(to: MovieEntity, payload: Any?): MovieResponse {
        throw notImplementedException()
    }
}