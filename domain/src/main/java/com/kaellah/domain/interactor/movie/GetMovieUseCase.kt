package com.kaellah.domain.interactor.movie

import com.kaellah.domain.entity.MovieEntity
import com.kaellah.domain.interactor.UseCase.SingleUseCase
import com.kaellah.domain.repository.movie.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repo: MovieRepository) : SingleUseCase<MovieEntity> {

    var movieId: Int = 0

    override fun execute(): Single<MovieEntity> = repo.getMovie(movieId)

}