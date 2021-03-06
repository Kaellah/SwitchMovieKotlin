package com.kaellah.data.repository.movie

import com.artemkopan.utils.ExtraUtils
import com.kaellah.data.BuildConfig
import com.kaellah.data.api.MoviesService
import com.kaellah.data.dao.movie.MovieDao
import com.kaellah.data.mapper.api.MoviesEntityMapper
import com.kaellah.data.mapper.api.MoviesMapper
import com.kaellah.data.mapper.database.MoviesDaoMapper
import com.kaellah.data.util.handleApiException
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.repository.movie.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * @since 12/20/17
 */
class MovieDataRepository(private val moviesService: MoviesService,
                          private val movieDao: MovieDao) : MovieRepository {

    override fun getMovies(page: Int): Single<List<MovieEntity>> {
        return moviesService
                .getMovies(BuildConfig.API_KEY, page)
                .subscribeOn(Schedulers.io())
                .handleApiException()
                .doOnSuccess { movieDao.insert(MoviesDaoMapper.mapList(it.results, null)) }
                .map {
                    ExtraUtils.checkBackgroundThread()
                    MoviesMapper.mapList(it.results, null)
                }
    }

    override fun getMovie(movieId: Int): Single<MovieEntity> {
        return movieDao
                .getMovie(movieId)
                .subscribeOn(Schedulers.io())
                .map {
                    ExtraUtils.checkBackgroundThread()
                    MoviesEntityMapper.map(it, null)
                }
    }
}