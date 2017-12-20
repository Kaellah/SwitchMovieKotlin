package com.kaellah.domain.util;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.observables.ConnectableObservable;

public class RxConnectableObservableDelegate<M> extends RxConnectableDelegate<Observable<M>, M> {

    private ConnectableObservable<M> connectableObservable;

    public RxConnectableObservableDelegate(ConnectableObservable<M> observable, @NonNull DisposableContainer connectionDisposable) {
        super(connectionDisposable);
        this.connectableObservable = observable;
    }

    public Observable<M> connect(boolean fetch, boolean resetOnDispose) {
        if (needConnect(fetch, resetOnDispose)) {
            connectableObservable.connect(connectionDisposable::add);
        }

        if (resetOnDispose) {
            return connectableObservable
                    .doOnDispose(getCancelAction())
                    .doOnNext(getNextConsumer())
                    .doOnError(getThrowableConsumer());
        } else {
            return connectableObservable
                    .doOnNext(getNextConsumer())
                    .doOnError(getThrowableConsumer());
        }
    }


}
