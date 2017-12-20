package com.kaellah.domain


object Constants {

    const val VIDEO_LENGTH_30 = 30 // seconds  // use with feeds posts
    const val VIDEO_LENGTH_150 = 150 // seconds // use with chat posts
    const val MAX_COMMENTS_IN_FEED = 3

    const val SHARE_MAX_MEDIAS = 6 // max media items (api requirements)
    const val SHARE_MAX_CHAT_IDS = 5 // max chat ids for one socket push (api requirements)


    const val RESEND_TIME = 20_000 //millis

    const val EMPTY = ""
    const val ZERO = "0"


    object Image {
        const val IMAGE_WIDTH = 342
        const val IMAGE_HEIGHT = IMAGE_WIDTH * 3

        const val FORMAT_IMAGE_URL = "http://image.tmdb.org/t/p/w%1\$d%2\$s"
    }

    object Headers {
        const val APPLY_OFFLINE_CACHE = "ApplyOfflineCache"
        const val APPLY_OFFLINE_CACHE_FORMATTED = "$APPLY_OFFLINE_CACHE: true"
        const val APPLY_API_AUTH = "AddApiAuth"
    }

    object SaveState {
        const val LAYOUT_MANAGER = "LAYOUT_MANAGER"
    }

    object Preference {
        const val AUTH_TOKEN = "AUTH_TOKEN"
        const val AUTH_TOKEN_PAYMENT = "AUTH_TOKEN_PAYMENT"
        const val USER_ID = "USER_ID"
    }

    object Tag {
        const val SWIPE_PROGRESS = 991
        const val LIST_PROGRESS = 992
    }

    object Field {
        const val FIRST_NAME = 1111
        const val LAST_NAME = 1112
    }

    object Extra {
        const val EXTRA_MEDIA_FILES = "local.EXTRA_MEDIA_FILES"
        const val EXTRA_MEDIA_FILE = "local.EXTRA_MEDIA_FILE"

        const val EXTRA_IS_LOGIN = "extra.EXTRA_IS_LOGIN"
        const val EXTRA_MEDIA_BEHAVIOUR = "extra.EXTRA_MEDIA_BEHAVIOUR"

        const val EXTRA_POST_ID = "extra.EXTRA_POST_ID"
        const val EXTRA_CHAT_ID = "extra.EXTRA_CHAT_ID"
        const val EXTRA_MESSAGE = "extra.EXTRA_MESSAGE"
        const val EXTRA_POST = "extra.EXTRA_POST"

        const val EXTRA_COUNTRY_CODE = "extra.EXTRA_COUNTRY_CODE"
        const val EXTRA_PHONE_NUMBER = "extra.EXTRA_PHONE_NUMBER"

        const val EXTRA_USER_ID = "extra.EXTRA_USER_ID"
        const val EXTRA_CARD_ID = "extra.EXTRA_CARD_ID"
    }

}