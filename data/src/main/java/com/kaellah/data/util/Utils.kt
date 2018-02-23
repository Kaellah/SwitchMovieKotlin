package com.kaellah.data.util

import android.support.annotation.WorkerThread
import com.kaellah.domain.Constants.Image
import timber.log.Timber
import java.net.InetSocketAddress
import java.net.Socket
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @since 12/20/17
 */
object Utils {

    private val FORMAT_INCOMING = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    private val FORMAT_FINISH = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)

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

    @JvmStatic
    @WorkerThread
    fun convertDate(dateString: String): String {
        val date: Date
        try {
            date = FORMAT_INCOMING.parse(dateString)

        } catch (e: ParseException) {
            e.printStackTrace()
            return dateString
        }

        return FORMAT_FINISH.format(date)
    }
}