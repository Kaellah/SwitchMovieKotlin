package com.kaellah.switchappkotlin.ui.movies

import android.annotation.TargetApi
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import com.artemkopan.utils.ExtraUtils
import com.artemkopan.utils.router.Router
import com.kaellah.switchappkotlin.R
import com.kaellah.switchappkotlin.dependency.Injectable
import com.kaellah.switchappkotlin.ui.movies.list.MoviesListFragment
import com.kaellah.switchappkotlin.view.base.BaseActivity

/**
 * @since 12/20/17
 */
class MoviesActivity : BaseActivity<MoviesViewModule>(), Injectable {

    override fun getContentView(): Int = R.layout.activity_base

    override fun getViewModelClass(): Class<MoviesViewModule> = MoviesViewModule::class.java

    @TargetApi(VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = MoviesListFragment.newInstance()
            if (ExtraUtils.postLollipop()) fragment.reenterTransition = Slide(Gravity.START)
            Router.fragment()
                    .useCustomAnim(false)
                    .addToBackStack(false)
                    .setIdRes(R.id.frameContainer)
                    .setFragment(fragment)
                    .start(this)
        }
    }
}