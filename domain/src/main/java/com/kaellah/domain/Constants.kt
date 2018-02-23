package com.kaellah.domain


object Constants {

    object Image {
        const val IMAGE_WIDTH = 342
        const val IMAGE_HEIGHT = IMAGE_WIDTH * 3

        const val FORMAT_IMAGE_URL = "http://image.tmdb.org/t/p/w%1\$d%2\$s"
    }

    object Headers {
        const val APPLY_OFFLINE_CACHE = "ApplyOfflineCache"
    }

    object Extra {
        const val EXTRA_MOVIE_ID = "local.EXTRA_MOVIE_ID"
    }
}