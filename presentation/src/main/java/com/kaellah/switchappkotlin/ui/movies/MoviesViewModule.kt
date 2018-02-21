package com.kaellah.switchappkotlin.ui.movies

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.DiffResult
import com.artemkopan.utils.CollectionUtils
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.interactor.movie.GetMoviesUseCase
import com.kaellah.switchappkotlin.ui.movies.list.MoviesAdapter
import com.kaellah.switchappkotlin.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @since 12/20/17
 */
class MoviesViewModule @Inject constructor(private val moviesUseCase: GetMoviesUseCase) : BaseViewModel() {

    init {
        moviesUseCase.clearedDisposable = onClearedDisposable
    }

    fun getMovies(fetch: Boolean, oldList: MutableList<MovieEntity>): Observable<Pair<List<MovieEntity>, DiffResult>> {
        if (fetch) {
            moviesUseCase.page = 1
            moviesUseCase.fetch = fetch

        } else if (!CollectionUtils.isEmpty(oldList)) {
            moviesUseCase.page = 1// oldList.lastOrNull()?.page todo need add pagination
        }
        return moviesUseCase.execute()
                .map { it to DiffUtil.calculateDiff(MoviesAdapter.Diff(oldList, it)) }
                .observeOn(AndroidSchedulers.mainThread())
    }
}