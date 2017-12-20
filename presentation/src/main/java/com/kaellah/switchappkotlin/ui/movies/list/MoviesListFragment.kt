package com.kaellah.switchappkotlin.ui.movies.list

import android.os.Bundle
import com.kaellah.switchappkotlin.R
import com.kaellah.switchappkotlin.dependency.Injectable
import com.kaellah.switchappkotlin.ui.movies.MoviesViewModule
import com.kaellah.switchappkotlin.view.base.BaseFragment

/**
 * @since 12/20/17
 */
class MoviesListFragment: BaseFragment<MoviesViewModule>(), Injectable {

    companion object {
        fun newInstance(): MoviesListFragment = MoviesListFragment()
    }

    override fun getContentView(): Int = R.layout.fragment_movies_list

    override fun getViewModelClass(): Class<MoviesViewModule> = MoviesViewModule::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}