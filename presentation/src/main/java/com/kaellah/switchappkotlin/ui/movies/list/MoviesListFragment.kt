package com.kaellah.switchappkotlin.ui.movies.list

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SimpleItemAnimator
import android.view.View
import com.artemkopan.recycler.listeners.OnItemClickListener
import com.artemkopan.utils.AnimUtils
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.switchappkotlin.R
import com.kaellah.switchappkotlin.dependency.Injectable
import com.kaellah.switchappkotlin.ui.movies.MoviesViewModel
import com.kaellah.switchappkotlin.ui.movies.details.MoviesDetailsFragment
import com.kaellah.switchappkotlin.utils.EndlessRecyclerViewScrollListener
import com.kaellah.switchappkotlin.view.base.BaseFragment
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movies_list.*
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

/**
 * @since 12/20/17
 */
class MoviesListFragment : BaseFragment<MoviesViewModel>(), Injectable, OnItemClickListener<MovieEntity> {

    companion object {
        private const val SPAN_COUNT = 2

        fun newInstance(): MoviesListFragment = MoviesListFragment()
    }

    @Inject
    lateinit var adapter: MoviesAdapter
    private var layoutManagerState: Parcelable? = null

    private val paginationListener by lazy(NONE) {
        object : EndlessRecyclerViewScrollListener(moviesRecyclerView.layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                getMovies(false, page)
            }
        }
    }

    override fun getContentView(): Int = R.layout.fragment_movies_list

    override fun getViewModelClass(): Class<MoviesViewModel> = MoviesViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarMovies.setNavigationOnClickListener { activity?.onBackPressed() }

        adapter.setOnItemClickListener(this)

        moviesRecyclerView.adapter = adapter
        moviesRecyclerView.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        (moviesRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        moviesRecyclerView.addOnScrollListener(paginationListener)

        getMovies(true, EndlessRecyclerViewScrollListener.STARTING_PAGE_INDEX)
    }

    private fun getMovies(fetch: Boolean, page: Int) {
        viewModel
                .getMovies(fetch, adapter.list, page)
                .subscribe({
                               paginationListener.disableLoading()

                               val isEmpty = adapter.isEmpty
                               adapter.setList(it.first, false)

                               if (layoutManagerState == null) {
                                   if (isEmpty) AnimUtils.animateLayout(moviesRecyclerView)
                                   adapter.notifyDataSetChanged()
                               } else {
                                   moviesRecyclerView.layoutManager.onRestoreInstanceState(layoutManagerState)
                                   layoutManagerState = null
                                   it.second.dispatchUpdatesTo(adapter)
                               }
                           },
                           { showError(it) })
                .addTo(onDestroyViewDisposable)
    }

    override fun onItemClickListener(view: View?, pos: Int, item: MovieEntity?, vararg transactionViews: View?) {
        if (item != null) {
            startFragment(MoviesDetailsFragment.newInstance(item.id), true)
        }
    }
}