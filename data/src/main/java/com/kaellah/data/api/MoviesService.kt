package com.kaellah.data.api

import com.kaellah.data.response.MoviesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @since 12/20/17
 */
interface MoviesService {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Single<MoviesListResponse>
}
