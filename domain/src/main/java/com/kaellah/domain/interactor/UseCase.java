package com.kaellah.domain.interactor;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface UseCase {

    interface FlowableUseCase<T> {

        @NotNull
        Flowable<T> execute();
    }

    interface MaybeUseCase<T> {

        @NotNull
        Maybe<T> execute();
    }

    interface ObservableUseCase<T> {

        @NotNull
        Observable<T> execute();
    }

    interface SingleUseCase<T> {

        @NotNull
        Single<T> execute();
    }

    interface CompletableUseCase {

        @NotNull
        Completable execute();
    }
}



