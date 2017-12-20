package com.kaellah.domain.interactor.movie

import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.interactor.UseCase.ObservableUseCase
import com.kaellah.domain.repository.movie.MovieRepository
import com.kaellah.domain.util.delegate
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Callable
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(private val repo: MovieRepository) : ObservableUseCase<List<MovieEntity>> {

    var page: Int? = null
        set(value) {
            field = value
            if (value == null) movies.clear()
        }

    var fetch: Boolean = false
        set(value) {
            field = value
            if (value) movies.clear()
        }

    lateinit var clearedDisposable: CompositeDisposable

    private var movies = mutableListOf<MovieEntity>()

    private val socialFeedObservable by lazy {
        Single.fromCallable { Callable { page } }
                .flatMap { repo.getMovies(it.call()!!) }
                .map {
                    movies.addAll(it)
                    val items: List<MovieEntity> = ArrayList(movies)
                    items
                }
                .toObservable()
                .replay(1)
                .delegate(clearedDisposable)
    }


    override fun execute(): Observable<List<MovieEntity>> = socialFeedObservable.connect(fetch)
}