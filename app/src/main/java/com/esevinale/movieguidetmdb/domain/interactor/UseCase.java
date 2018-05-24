package com.esevinale.movieguidetmdb.domain.interactor;

import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class UseCase<T, Params> {

    private final CompositeDisposable mDisposables;
    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;

    UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.mThreadExecutor = threadExecutor;
        this.mPostExecutionThread = postExecutionThread;
        mDisposables = new CompositeDisposable();
    }

    abstract Flowable<T> buildUseCaseFlowable(Params params);

    public void execute(DisposableSubscriber<T> subscriber, Params params) {
        final Flowable<T> flowable = buildUseCaseFlowable(params)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler());

        addDisposable(flowable.subscribeWith(subscriber));
    }

    public void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    public void clear() {
        mDisposables.clear();
    }

    private void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }
}
