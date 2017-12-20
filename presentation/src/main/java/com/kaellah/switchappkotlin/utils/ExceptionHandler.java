package com.kaellah.switchappkotlin.utils;

import android.content.Context;
import android.text.TextUtils;


import com.kaellah.data.error.ApiException;
import com.kaellah.data.error.EmptyFieldException;
import com.kaellah.data.error.SocketConnectionException;
import com.kaellah.data.error.WrongFieldException;
import com.kaellah.switchappkotlin.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import timber.log.Timber;

public class ExceptionHandler {

    public static String handleException(Context context, Throwable throwable) {
        Timber.w(throwable);

        if (context == null) return "";

        String message;

        if (throwable instanceof UnknownHostException ||
            throwable instanceof ConnectException) {
            return context.getString(R.string.error_internet_connection);
        } else if (throwable instanceof SocketTimeoutException) {
            return context.getString(R.string.error_internet_timeout);
        } else if (throwable instanceof EmptyFieldException) {
            return context.getString(R.string.error_empty_field);
        } else if (throwable instanceof WrongFieldException) {
            return context.getString(((WrongFieldException) throwable).getMessageRes());
        } else if (throwable instanceof SocketConnectionException) {
            return context.getString(R.string.error_socket_connect);
        } else if (throwable instanceof ApiException) {
            ApiException apiException = (ApiException) throwable;
            switch (apiException.getCode()) {
                case 504: {
                    return context.getString(R.string.error_internet_connection);
                }
                case 401: {
                    return apiException.getMessage();
                }
                default: {
                    message = throwable.getMessage();
                }
            }
        } else {
            message = throwable.getMessage();
        }

        if (TextUtils.isEmpty(message)) {
            message = context.getString(R.string.error_undefined);
        }

        return message;
    }

}
