package com.kaellah.domain.util;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableContainer;

public abstract class RxConnectableDelegate<Type, Model> {

    protected final DisposableContainer connectionDisposable;
    private AtomicBoolean isLoaded = new AtomicBoolean(false);
    private AtomicBoolean isLoading = new AtomicBoolean(false);
    private AtomicBoolean isDisposed = new AtomicBoolean(false);

    protected RxConnectableDelegate(@NonNull DisposableContainer connectionDisposable) {
        this.connectionDisposable = connectionDisposable;
    }

    public Type connect() {
        return connect(false, true);
    }

    public Type connect(boolean fetch) {
        return connect(fetch, true);
    }

    public abstract Type connect(boolean fetch, boolean resetOnDispose);

    protected boolean needConnect(boolean fetch, boolean resetOnDispose) {
        if (resetOnDispose && isDisposed.get()) {
            isLoaded.set(false);
        }

        boolean connect = !isLoading.get() && (fetch || !isLoaded.get());
        if (connect) {
            isLoading.set(true);
        }
        return connect;
    }

    protected Consumer<Model> getNextConsumer() {
        return o -> {
            isLoaded.set(true);
            isLoading.set(false);
        };
    }

    protected Consumer<Throwable> getThrowableConsumer() {
        return throwable -> {
            isLoaded.set(false);
            isLoading.set(false);
        };
    }

    protected Action getCancelAction() {
        return () -> isDisposed.set(true);
    }

}
