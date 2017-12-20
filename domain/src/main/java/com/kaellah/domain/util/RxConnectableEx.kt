package com.kaellah.domain.util

import io.reactivex.annotations.NonNull
import io.reactivex.flowables.ConnectableFlowable
import io.reactivex.internal.disposables.DisposableContainer
import io.reactivex.observables.ConnectableObservable


fun <T> ConnectableObservable<T>.delegate(@NonNull connectionDisposable: DisposableContainer): RxConnectableObservableDelegate<T> {
    return RxConnectableObservableDelegate(this, connectionDisposable)
}

fun <T> ConnectableFlowable<T>.delegate(@NonNull connectionDisposable: DisposableContainer): RxConnectableFlowableDelegate<T> {
    return RxConnectableFlowableDelegate(this, connectionDisposable)
}