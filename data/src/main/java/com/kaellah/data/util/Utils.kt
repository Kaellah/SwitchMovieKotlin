package com.kaellah.data.util

import android.support.annotation.WorkerThread
import com.kaellah.domain.Constants.Image
import timber.log.Timber
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*

/**
 * @since 12/20/17
 */
object Utils {

    @JvmStatic
    @WorkerThread
    fun isOnline(): Boolean {
        try {
            val timeout = 1500
            val sock = Socket()
            val sockAddr = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockAddr, timeout)
            sock.close()

            return true
        } catch (ex: Exception) {
            Timber.e(ex)
        }
        return false
    }

    @JvmStatic
    @WorkerThread
    fun getCorrectImageUrl(posterPath: String, width: Int): String {
        return String.format(Locale.getDefault(), Image.FORMAT_IMAGE_URL, width, posterPath)
    }
}