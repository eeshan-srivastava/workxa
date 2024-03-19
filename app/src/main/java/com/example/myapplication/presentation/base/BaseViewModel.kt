package com.example.myapplication.presentation.base

import androidx.lifecycle.ViewModel
import com.example.domain.model.StandardError
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel(
) : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun <T : Any, W : Any> fetchRemoteData(
        observable: Observable<T>,
        mapper: (t: T) -> W,
        onSuccess: (data: W) -> Unit,
        onError: (error: StandardError) -> Unit
    ) {
        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    mapper.invoke(it)
                }
                .subscribe({
                    onSuccess.invoke(it)
                }, {
                    onError.invoke(StandardError(developerMessage = "ERROR"))
                })
        )
    }

    protected fun <T : Any, W : Any> fetchLocalData(
        observable: Observable<T>,
        mapper: (t: T) -> W,
        onSuccess: (data: W) -> Unit,
        onError: (error: StandardError) -> Unit
    ) {
        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    mapper.invoke(it)
                }
                .subscribe({
                    onSuccess.invoke(it)
                }, {
                    onError.invoke(StandardError(developerMessage = "ERROR"))
                })
        )
    }


    protected fun executeLocalCompletable(
        completable: Completable,
        onSuccess: () -> Unit,
        onError: (error: StandardError) -> Unit
    ) {
        compositeDisposable.add(
            completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { onSuccess.invoke() }
                .subscribe(
                    {
                    },
                    { error ->
                        onError.invoke(
                            StandardError(
                                developerMessage = "Error occurred: ${error.message}"
                            )
                        )
                    }
                )
        )
    }



    override fun onCleared() {
        if (compositeDisposable.isDisposed.not())
            compositeDisposable.dispose()
        super.onCleared()
    }

}