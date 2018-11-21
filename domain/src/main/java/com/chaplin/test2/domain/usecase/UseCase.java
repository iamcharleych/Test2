package com.chaplin.test2.domain.usecase;

import com.chaplin.test2.domain.execution.ExecutionThread;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class UseCase<RESULT, PARAMS> {

    private ExecutionThread mWorkerThread;
    private ExecutionThread mObserverThread;
    private CompositeDisposable mSubscriptions;

    public UseCase(ExecutionThread mWorkerThread, ExecutionThread mObserverThread) {
        this.mWorkerThread = mWorkerThread;
        this.mObserverThread = mObserverThread;
        this.mSubscriptions = new CompositeDisposable();
    }

    public abstract Flowable<RESULT> createObservable(PARAMS params);

    public final void execute(PARAMS params, Subscriber<RESULT> subscriber) {
        if (subscriber == null) {
            return; // early exit
        }

        Disposable subscription = createObservable(params)
                .subscribeOn(mWorkerThread.getScheduler())
                .observeOn(mObserverThread.getScheduler())
                .subscribe(
                        subscriber::onNext,
                        subscriber::onError,
                        subscriber::onComplete,
                        subscriber::onSubscribe
                );
        addSubscription(subscription);
    }

    public void dispose() {
        if (!mSubscriptions.isDisposed()) {
            mSubscriptions.dispose();
        }
    }

    private void addSubscription(Disposable subscription) {
        if (subscription == null) {
            return; // early exit
        }

        mSubscriptions.add(subscription);
    }
}
