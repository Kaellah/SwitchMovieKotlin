package com.kaellah.switchappkotlin.viewmodel;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable onClearedDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        onClearedDisposable.clear();
        super.onCleared();
    }
}
