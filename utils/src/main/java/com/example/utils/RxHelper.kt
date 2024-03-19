package com.example.utils

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference


fun <T : Any> observable(execute: (weakEmitter: SoftReference<ObservableEmitter<T>>) -> Unit): Observable<T> {
    return Observable.create { emitter ->
        val it = SoftReference(emitter)
        execute.invoke(it)
    }
}

fun completable(execute: (weakEmitter: SoftReference<CompletableEmitter>) -> Unit): Completable {
    return Completable.create { emitter ->
        val softEmitter = SoftReference(emitter)
        execute.invoke(softEmitter)
    }
}

fun <T : Any> SoftReference<ObservableEmitter<T>>.unDisposedObservable(execute: (emitter: ObservableEmitter<T>) -> Unit) {
    this.get()?.run {
        if (!isDisposed) {
            execute.invoke(this)
        }
    }
}

fun <T: Any> SoftReference<ObservableEmitter<T>>.onSuccessObservable(t: T) {
    this.unDisposedObservable {
        it.onNext(t)
    }
}

fun <T:Any> SoftReference<ObservableEmitter<T>>.onCompeteObservable() {
    this.unDisposedObservable {
        it.onComplete()
    }
}

fun <T:Any> SoftReference<ObservableEmitter<T>>.onErrorObservable(t: Throwable) {
    this.unDisposedObservable {
        it.onError(t)
    }
}


fun <T: Any> singleObserver(execute: (weakEmitter: WeakReference<SingleEmitter<T>>) -> Unit): Single<T> {
    return Single.create { emitter ->
        val it = WeakReference(emitter)
        execute.invoke(it)
    }
}

fun <T: Any> SoftReference<SingleEmitter<T>>.unDisposed(execute: (emitter: SingleEmitter<T>) -> Unit) {
    this.get()?.run {
        if (!isDisposed) {
            execute.invoke(this)
        }
    }
}

fun <T: Any> SoftReference<SingleEmitter<T>>.onSuccess(t: T) {
    this.unDisposed {
        it.onSuccess(t)
    }
}

fun <T: Any> SoftReference<SingleEmitter<T>>.onError(t: Throwable) {
    this.unDisposed {
        it.onError(t)
    }
}

