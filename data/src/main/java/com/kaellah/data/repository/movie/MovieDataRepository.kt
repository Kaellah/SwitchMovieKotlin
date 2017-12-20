package com.kaellah.data.repository.movie

import com.artemkopan.utils.ExtraUtils
import com.kaellah.data.api.MoviesService
import com.kaellah.data.mapper.api.MoviesMapper
import com.kaellah.data.util.handleApiException
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.repository.movie.MovieRepository
import io.reactivex.Single

/**
 * @since 12/20/17
 */
class MovieDataRepository(private val moviesService: MoviesService) : MovieRepository {

    override fun getMovies(page: Int): Single<List<MovieEntity>> {
        return moviesService
                .getMovies("", page)
                .handleApiException()
                .map {
                    ExtraUtils.checkBackgroundThread()
                    MoviesMapper.mapList(it.results, null)
                }
    }
}