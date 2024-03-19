package com.example.data.local.db


import android.annotation.SuppressLint
import com.example.domain.model.ErrorCodes
import com.example.domain.model.StandardError
import com.example.utils.completable
import com.example.utils.observable
import com.example.utils.onErrorObservable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import java.lang.ref.SoftReference


@SuppressLint("CheckResult")
fun <T : Any, W : Any, P: Any> makeDbCall(
    dbCall: Observable<W>,
    mapper: (emitter: SoftReference<ObservableEmitter<T>>, args: W) -> P,
    onSuccess: (emitter: SoftReference<ObservableEmitter<T>>, data: P) -> Unit
): Observable<T> {
    return observable { parent ->
        dbCall.map {
            mapper.invoke(parent, it)
        }.subscribe({
            onSuccess.invoke(parent, it)
        }, {
            parent.onErrorObservable(StandardError(
                responseCode = ErrorCodes.App.DB_EXCEPTION
            ))
        })
    }
}

@SuppressLint("CheckResult")
fun executeCompletableTask(
    task: Completable,
    onSuccess: (emitter: SoftReference<CompletableEmitter>) -> Unit
): Completable {
    return completable { parent ->
        task.subscribe({
            onSuccess.invoke(parent)
        }, { error ->
            parent.get()?.onError(
                StandardError(
                    responseCode = ErrorCodes.App.DB_EXCEPTION,
                    developerMessage = "Error occurred: ${error.message}"
                )
            )
        })
    }
}
