package com.kaellah.switchappkotlin.ui.movies.details

import android.os.Bundle
import com.kaellah.switchappkotlin.R
import com.kaellah.switchappkotlin.dependency.Injectable
import com.kaellah.switchappkotlin.ui.movies.MoviesViewModule
import com.kaellah.switchappkotlin.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies_list.*

/**
 * @since 12/20/17
 */
class MoviesDetailsFragment : BaseFragment<MoviesViewModule>(), Injectable {

    companion object {
        fun newInstance(): MoviesDetailsFragment = MoviesDetailsFragment()
    }

    override fun getContentView(): Int = R.layout.fragment_movies_list

    override fun getViewModelClass(): Class<MoviesViewModule> = MoviesViewModule::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarMovies.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    override fun onStart() {
        super.onStart()
//        viewModel
//                .getMovies(true, adapter.list)
//                .subscribe({
//                               val isEmpty = adapter.isEmpty
//                               adapter.setList(it.first, false)
//
//                               if (layoutManagerState == null) {
//                                   if (isEmpty) AnimUtils.animateLayout(moviesRecyclerView)
//                                   adapter.notifyDataSetChanged()
//                               } else {
//                                   moviesRecyclerView.layoutManager.onRestoreInstanceState(layoutManagerState)
//                                   layoutManagerState = null
//                                   it.second.dispatchUpdatesTo(adapter)
//                               }
//                           },
//                           { showError(it) })
//                .addTo(onStopDisposable)
    }
}