package com.kaellah.switchappkotlin.ui.movies.list

import android.content.Context
import android.view.View
import com.artemkopan.recycler.holder.BaseHolder
import com.kaellah.data.util.Utils
import com.kaellah.domain.Constants
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.switchappkotlin.utils.GlideSource
import com.kaellah.switchappkotlin.utils.loadClear
import com.kaellah.switchappkotlin.utils.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_item_movie.*

class MovieViewHolder(override val containerView: View) : BaseHolder<MovieEntity>(containerView), LayoutContainer {

    override fun bind(context: Context?, item: MovieEntity, position: Int) {
        val url = Utils.getCorrectImageUrl(item.posterPath, Constants.Image.IMAGE_WIDTH)
        movieImageView.loadImage(GlideSource.View(), url)
    }

    override fun clear() {
        movieImageView.loadClear()
    }
}