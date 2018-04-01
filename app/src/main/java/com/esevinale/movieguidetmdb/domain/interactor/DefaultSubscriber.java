package com.esevinale.movieguidetmdb.domain.interactor;

import io.reactivex.subscribers.DisposableSubscriber;

public class DefaultSubscriber<T> extends DisposableSubscriber<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
