package com.kaellah.switchappkotlin.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.Px
import android.support.v4.app.FragmentActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.kaellah.switchappkotlin.R


private const val NO_OVERRIDE = -1

fun ImageView.loadImageCircle(source: GlideSource = GlideSource.View(),
                              model: Any?,
                              @Px width: Int = NO_OVERRIDE,
                              @Px height: Int = NO_OVERRIDE,
                              errorDrawable: GlideHolder = GlideHolder.Res(R.drawable.ph_avatar),
                              placeholderDrawable: GlideHolder = GlideHolder.Res(R.drawable.ph_avatar),
                              animate: Boolean = false,
                              skipMemoryCache: Boolean = false,
                              diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.ALL,
                              requestListener: RequestListener<Drawable>? = null,
                              vararg transformations: Transformation<Bitmap>) {
    val newTransformations = arrayOf(*transformations, CircleCrop())
    loadImage(source, model, width, height, errorDrawable, placeholderDrawable, false, animate,
              skipMemoryCache, diskCacheStrategy, requestListener, *newTransformations)
}


@SuppressLint("CheckResult")
@Suppress("UNUSED_EXPRESSION")
fun ImageView.loadImage(
        source: GlideSource = GlideSource.View(),
        model: Any?,
        @Px width: Int = NO_OVERRIDE,
        @Px height: Int = NO_OVERRIDE,
        errorDrawable: GlideHolder = GlideHolder.Res(R.drawable.ph_no_photo),
        placeholderDrawable: GlideHolder = GlideHolder.Res(R.drawable.ph_loading),
        centerCrop: Boolean = true,
        animate: Boolean = false,
        skipMemoryCache: Boolean = false,
        diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC,
        requestListener: RequestListener<Drawable>? = null,
        vararg transformations: Transformation<Bitmap>) {

    if (model == null) {
        when (errorDrawable) {
            is Drawable -> this.setImageDrawable(errorDrawable)
            is GlideHolder.Res -> this.setImageResource(errorDrawable.res)
        }
        return
    }

    val request = when (source) {
        is GlideSource.View -> Glide.with(this)
        is GlideSource.Context -> Glide.with(context.applicationContext)
        is GlideSource.Activity -> Glide.with(source.activity)
        is GlideSource.Fragment -> Glide.with(source.fragment)
    }
            .asDrawable()
            .load(model)


    val options = RequestOptions()

    when (errorDrawable) {
        is Drawable -> options.error(errorDrawable)
        is GlideHolder.Res -> options.error(errorDrawable.res)
    }

    when (placeholderDrawable) {
        is Drawable -> options.placeholder(placeholderDrawable)
        is GlideHolder.Res -> options.placeholder(placeholderDrawable.res)
    }

    options.fitCenter()

    if (centerCrop && transformations.isEmpty()) {
        options.centerCrop()

    } else if (centerCrop && transformations.isNotEmpty()) {
        options.transform(MultiTransformation(CenterCrop(), *transformations))

    } else if (transformations.isNotEmpty()) {
        options.transform(MultiTransformation(*transformations))

    } else {
        options
    }

    if (animate) {
        request.transition(DrawableTransitionOptions.withCrossFade())
    } else {
        options.dontAnimate()
    }

    if (width != NO_OVERRIDE && height != NO_OVERRIDE) {
        options.override(width, height)
    }

    if (requestListener != null) {
        request.listener(requestListener)
    }

    if (skipMemoryCache) {
        options.skipMemoryCache(true)
    }

    options.diskCacheStrategy(diskCacheStrategy)

    request.apply(options)
    request.into(this)
}

fun ImageView.loadClear(source: GlideSource = GlideSource.View()) {
    when (source) {
        is GlideSource.View -> Glide.with(this)
        is GlideSource.Context -> Glide.with(this.context)
        is GlideSource.Activity -> Glide.with(source.activity)
        is GlideSource.Fragment -> Glide.with(source.fragment)
    }.clear(this)
}

sealed class GlideSource {
    class View : GlideSource()
    class Context(val context: android.content.Context) : GlideSource()
    class Activity(val activity: FragmentActivity) : GlideSource()
    class Fragment(val fragment: android.support.v4.app.Fragment) : GlideSource()
}

sealed class GlideHolder {
    class Drawable(val drawable: android.graphics.drawable.Drawable = ColorDrawable(Color.GRAY)) : GlideHolder()
    class Res(@DrawableRes val res: Int) : GlideHolder()
}
