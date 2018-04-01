package com.esevinale.movieguidetmdb.domain.interactor;

import android.util.Log;

import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class UseCase<T, Params> {

    private final CompositeDisposable disposables;
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        disposables = new CompositeDisposable();
    }

    abstract Flowable<T> buildUseCaseFlowable(Params params);

    public void execute(DisposableSubscriber<T> subscriber, Params params) {
        final Flowable<T> flowable = buildUseCaseFlowable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

        addDisposable(flowable.subscribeWith(subscriber));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
