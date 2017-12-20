package com.kaellah.switchappkotlin.view.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;


import com.kaellah.switchappkotlin.R;
import com.kaellah.switchappkotlin.utils.ExceptionHandler;

import java.lang.ref.WeakReference;

class PresentationDelegate {

    private final WeakReference<Context> context;
    private ProgressDialog progressDialog;

    PresentationDelegate(@NonNull Context context) {
        this.context = new WeakReference<>(context);
    }

    void showError(Throwable throwable) {
        showError(ExceptionHandler.handleException(context.get(), throwable));
    }

    void showError(@StringRes int errorRes) {
        if (context.get() != null)
            showError(context.get().getString(errorRes));
    }

    void showError(String error) {
        if (context.get() != null)
            Toast.makeText(context.get(), error, Toast.LENGTH_LONG).show();
    }

    void showProgress() {
        if (context.get() == null) return;
        if (progressDialog != null && progressDialog.isShowing()) return;

        progressDialog = new ProgressDialog(context.get());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(context.get().getString(R.string.base_info_loading));
        progressDialog.show();
    }

    void hideProgress() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

}
