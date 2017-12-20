package com.kaellah.data.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import com.kaellah.data.error.ApiException
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import retrofit2.HttpException


fun Completable.handleApiException(): Completable {
    return this.onErrorResumeNext(ExceptionHandler.handleCompletable())
}

fun <T> Single<T>.handleApiException(): Single<T> {
    return this.onErrorResumeNext(ExceptionHandler.handleSingle())
}

object ExceptionHandler {

    fun handleCompletable(): Function<Throwable, CompletableSource> {
        return Function {
            return@Function Completable.error(convertException(it))
        }
    }

    fun <T> handleSingle(): Function<Throwable, SingleSource<T>> {
        return Function {
            return@Function Single.error(convertException(it))
        }
    }

    private fun convertException(throwable: Throwable): Throwable {
        if (throwable !is HttpException) {
            return throwable
        }

        val code = throwable.code()

        val response = throwable.response().errorBody()?.string()

        if (response != null) {
            val json = JsonParser().parse(response)
            val jsonObject = json.asJsonObject
            if (jsonObject.has("errors")) {

                val errorsElement = jsonObject.get("errors")

                when {
                    errorsElement.isJsonObject -> parseJsonObject(errorsElement.asJsonObject)
                    errorsElement.isJsonPrimitive -> parseJsonPrimitive(errorsElement.asJsonPrimitive)
                    errorsElement.isJsonArray -> parseJsonArray(errorsElement.asJsonArray)
                    else -> ""
                }.let {
                    if (it.isNotBlank()) return ApiException(code, it)
                }

            }
        }

        return ApiException(code, throwable.message())
    }


    private fun parseJsonObject(jsonObject: JsonObject): String {
        val builder = StringBuilder()

        val size = jsonObject.size()
        jsonObject.entrySet().forEachIndexed { index, mutableEntry ->
            builder.append(mutableEntry.value.asString)
            if (index < size - 1) {
                builder.append('\n')
            }
        }
        return builder.toString()
    }

    private fun parseJsonArray(jsonArray: JsonArray): String {
        val builder = StringBuilder()
        val size = jsonArray.size()
        jsonArray.forEachIndexed { index, jsonElement ->
            val errorObject = jsonElement.asJsonObject
            errorObject.entrySet().forEach { key ->
                builder.append(key.value.asString)
            }
            if (index < size - 1) {
                builder.append('\n')
            }
        }
        return builder.toString()
    }

    private fun parseJsonPrimitive(jsonPrimitive: JsonPrimitive): String {
        return jsonPrimitive.asString
    }
}