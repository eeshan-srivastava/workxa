package com.example.data.network

import android.annotation.SuppressLint
import com.example.domain.model.ErrorCodes
import com.example.domain.model.StandardError
import com.example.utils.observable
import com.example.utils.onErrorObservable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import java.io.IOException
import java.lang.ref.SoftReference
import java.net.UnknownHostException


@SuppressLint("CheckResult")
fun <T : Any, W : Any, P: Any> makeApiRequest(
    apiCall: Observable<W>,
    mapper: (emitter: SoftReference<ObservableEmitter<T>>, args: W) -> P,
    onSuccess: (emitter: SoftReference<ObservableEmitter<T>>, data: P) -> Unit
): Observable<T> {
    return observable { parent ->
        apiCall.map {
            mapper.invoke(parent, it)
        }.subscribe({
            onSuccess.invoke(parent, it)
        }, {
            val msg = when (it) {
                is UnknownHostException -> {
                    "Unable to connect to internet"
                }
                is IOException -> {
                    "Unable to connect to internet"
                }
                else -> {
                    "Something went wrong"
                }
            }
            parent.onErrorObservable(StandardError(
                responseCode = ErrorCodes.App.NO_INTERNET,
                developerMessage = it.message ?: "",
                serverMessage = msg
            ))
        })
    }
}
