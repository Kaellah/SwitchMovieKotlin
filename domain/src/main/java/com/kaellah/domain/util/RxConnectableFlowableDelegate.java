package com.kaellah.domain.util;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.internal.disposables.DisposableContainer;

public class RxConnectableFlowableDelegate<M> extends RxConnectableDelegate<Flowable<M>, M> {

    private ConnectableFlowable<M> connectableFlowable;

    public RxConnectableFlowableDelegate(ConnectableFlowable<M> observable, @NonNull DisposableContainer connectionDisposable) {
        super(connectionDisposable);
        this.connectableFlowable = observable;
    }

    @Override
    public Flowable<M> connect(boolean fetch, boolean resetOnDispose) {
        if (needConnect(fetch, resetOnDispose)) {
            connectableFlowable.connect(connectionDisposable::add);
        }
        return connectableFlowable
                .doOnCancel(getCancelAction())
                .doOnNext(getNextConsumer())
                .doOnError(getThrowableConsumer());
    }

}
