package com.yenen.ahmet.kotlinretrofitviewmodel.base

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel(protected val disposable: CompositeDisposable) : ViewModel() {

    private var dataViewBinding: ViewDataBinding? = null

    protected fun setViewDataBinding(viewBinding: ViewDataBinding) {
        dataViewBinding = viewBinding
    }

    protected fun <T> getObservableConfig(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed)
            disposable.dispose()
        dataViewBinding?.unbind()
        dataViewBinding = null
    }
}