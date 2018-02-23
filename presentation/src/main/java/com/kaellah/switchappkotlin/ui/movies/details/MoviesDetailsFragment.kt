package com.kaellah.switchappkotlin.ui.movies.details

import android.os.Bundle
import com.kaellah.data.util.Utils
import com.kaellah.domain.Constants
import com.kaellah.domain.Constants.Extra.EXTRA_MOVIE_ID
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.switchappkotlin.R
import com.kaellah.switchappkotlin.dependency.Injectable
import com.kaellah.switchappkotlin.ui.movies.MoviesViewModel
import com.kaellah.switchappkotlin.utils.GlideSource
import com.kaellah.switchappkotlin.utils.loadImage
import com.kaellah.switchappkotlin.view.base.BaseFragment
import io.reactivex.rxkotlin.addTo
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_movie_details.*

/**
 * @since 12/20/17
 */
class MoviesDetailsFragment : BaseFragment<MoviesViewModel>(), Injectable {

    companion object {
        fun newInstance(movieId: Int) = MoviesDetailsFragment().apply {
            arguments = Bundle().apply { putInt(EXTRA_MOVIE_ID, movieId) }
        }
    }

    private var movieId: Int = 0

    override fun getContentView(): Int = R.layout.fragment_movie_details

    override fun getViewModelClass(): Class<MoviesViewModel> = MoviesViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieId = arguments?.getInt(EXTRA_MOVIE_ID)!!

        toolbarMovie.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    override fun onStart() {
        super.onStart()
        viewModel
                .getMovie(movieId)
                .subscribe({ updateFields(it) },
                           { showError(it) })
                .addTo(onStopDisposable)
    }

    private fun updateFields(movie: MovieEntity) {
        val url = Utils.getCorrectImageUrl(movie.posterPath, Constants.Image.IMAGE_WIDTH)
        movieImageView.loadImage(GlideSource.Fragment(this), url)

        backgroundImageView.loadImage(GlideSource.Fragment(this), url, transformations = *arrayOf(BlurTransformation(25)))

        titleTextViewValue.text = movie.title
        toolbarMovie.title = movie.title
        scoreTextViewValue.text = movie.voteAverage.toString()
        ratingTextViewValue.text = if (movie.adult) getString(R.string.Rating) else getString(R.string.PG)
        releaseTextViewValue.text = Utils.convertDate(movie.releaseDate)
        overviewTextViewValue.text = movie.overview
    }
}