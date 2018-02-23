package com.kaellah.switchappkotlin.ui.movies

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.DiffResult
import com.artemkopan.utils.CollectionUtils
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.interactor.movie.GetMovieUseCase
import com.kaellah.domain.interactor.movie.GetMoviesUseCase
import com.kaellah.switchappkotlin.ui.movies.list.MoviesAdapter
import com.kaellah.switchappkotlin.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @since 12/20/17
 */
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase,
                                          private val getMovieUseCase: GetMovieUseCase) : BaseViewModel() {

    init {
        getMoviesUseCase.clearedDisposable = onClearedDisposable
    }

    fun getMovies(fetch: Boolean, oldList: MutableList<MovieEntity>, page: Int): Observable<Pair<List<MovieEntity>, DiffResult>> {
        if (fetch) {
            getMoviesUseCase.run { this.page = page; this.fetch = fetch }

        } else if (!CollectionUtils.isEmpty(oldList)) {
            getMoviesUseCase.run { this.page = page }
        }
        return getMoviesUseCase.execute()
                .map { it to DiffUtil.calculateDiff(MoviesAdapter.Diff(oldList, it)) }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovie(movieId: Int): Single<MovieEntity> {
        return getMovieUseCase
                .apply { this.movieId = movieId }
                .execute()
                .observeOn(AndroidSchedulers.mainThread())
    }
}