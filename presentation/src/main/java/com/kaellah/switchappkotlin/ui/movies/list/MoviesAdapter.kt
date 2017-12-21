package com.kaellah.switchappkotlin.ui.movies.list

import android.view.ViewGroup
import com.artemkopan.recycler.adapter.RecyclerListAdapter
import com.artemkopan.recycler.diff.BaseDiffCallback
import com.artemkopan.utils.ViewUtils
import com.kaellah.domain.entity.MovieEntity
import com.kaellah.switchappkotlin.R
import javax.inject.Inject

class MoviesAdapter @Inject constructor() : RecyclerListAdapter<MovieEntity, MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val holder = MovieViewHolder(ViewUtils.inflateView(parent, R.layout.view_item_movie))
        holder.itemView.setOnClickListener { callOnItemClick(holder, it) }
        return holder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, model: MovieEntity, position: Int) {
        holder.bind(model, position)
    }

    class Diff(oldList: List<MovieEntity>?, newList: List<MovieEntity>?) : BaseDiffCallback<MovieEntity>(oldList, newList) {

        override fun areContentsTheSame(oldItem: MovieEntity?, newItem: MovieEntity?): Boolean {
            return oldItem != null && newItem != null &&
                    oldItem.title == newItem.title &&
                    oldItem.originalTitle == newItem.originalTitle
        }

        override fun areItemsTheSame(oldItem: MovieEntity?, newItem: MovieEntity?): Boolean {
            return oldItem?.id == newItem?.id
        }

    }
}