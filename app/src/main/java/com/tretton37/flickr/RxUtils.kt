package com.tretton37.flickr

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applyIOSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.applyComputationSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.computation()).unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applyComputationSchedulers(): Single<T> {
    return subscribeOn(Schedulers.computation()).unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applyIOSchedulers(): Single<T> {
    return subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun Disposable?.unSubscribeIfNotNull() {
    this?.dispose()
}

fun CompositeDisposable?.clearSubscriptions() {
    this?.clear()
}
