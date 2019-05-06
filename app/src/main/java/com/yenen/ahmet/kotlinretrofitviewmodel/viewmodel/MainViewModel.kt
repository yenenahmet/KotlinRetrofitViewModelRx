package com.yenen.ahmet.kotlinretrofitviewmodel.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.util.Log
import com.yenen.ahmet.kotlinretrofitviewmodel.adapter.RecyclerViewAdapter
import com.yenen.ahmet.kotlinretrofitviewmodel.base.BaseViewModel
import com.yenen.ahmet.kotlinretrofitviewmodel.databinding.ActivityMainBinding
import com.yenen.ahmet.kotlinretrofitviewmodel.model.Crypto
import com.yenen.ahmet.kotlinretrofitviewmodel.network.ApiClient
import com.yenen.ahmet.kotlinretrofitviewmodel.network.CryptocurrencyService
import com.yenen.ahmet.kotlinretrofitviewmodel.ui.MainActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

class MainViewModel : BaseViewModel(CompositeDisposable()) {

    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var cryptoMarkets: MutableLiveData<List<Crypto.Market>>? = null
    val handleMessage: MutableLiveData<String> = MutableLiveData()
    private val service:CryptocurrencyService =ApiClient.createService(CryptocurrencyService::class.java)

    fun init(savedInstanceState: Bundle? ,binding : ActivityMainBinding,activity:MainActivity) {
        if(savedInstanceState==null){
            recyclerViewAdapter = RecyclerViewAdapter()
        }
        setViewDataBinding(binding)
        binding.setLifecycleOwner(activity)
    }

    fun getData(): LiveData<List<Crypto.Market>> {
        if (cryptoMarkets == null) {
            cryptoMarkets = MutableLiveData()
            loadData()
        }
        return cryptoMarkets as LiveData<List<Crypto.Market>>
    }

    fun getAdapter(): RecyclerViewAdapter? {
        return recyclerViewAdapter
    }


    private fun mergeLists(): BiFunction<MutableList<Crypto.Market>, MutableList<Crypto.Market>, MutableList<Crypto.Market>> {
        return BiFunction { markets, markets2->
            markets.addAll(markets2)
            markets
        }
    }

    private fun loadData() {
        disposable.add(getObservableConfig(Observable.zip(getObservableBtc(),getObservableEth(),mergeLists()))
            .subscribe(this::handleResults, this::handleError))
    }

    private fun getObservableEth(): Observable<MutableList<Crypto.Market>>{
        return service.getCoinData("eth")
            .map<Observable<Crypto.Market>> { result -> Observable.fromIterable<Crypto.Market>(result.ticker?.markets) }
            .flatMap<Crypto.Market> { x -> x }.filter { y ->
                y.coinName = "eth"
                true
            }.toList().toObservable()
    }

    private fun getObservableBtc():Observable<MutableList<Crypto.Market>>{
        return service.getCoinData("btc")
            .map<Observable<Crypto.Market>> { result -> Observable.fromIterable<Crypto.Market>(result.ticker?.markets) }
            .flatMap<Crypto.Market> { x -> x }.filter { y ->
                y.coinName = "btc"
                true
            }.toList().toObservable()
    }

    private fun handleResults(crypto: List<Crypto.Market>?) {
        if (crypto != null && crypto.size > 0) {
            Log.e("Handler =", crypto[0].coinName + crypto.size.toString())
            Log.e("End Get Data Time =", System.currentTimeMillis().toString())
            cryptoMarkets!!.value = crypto
            handleMessage.setValue("Success")
        } else {
            handleMessage.setValue("NO RESULTS FOUND")
        }
    }

    private fun handleError(t: Throwable) {
        handleMessage.setValue(ApiClient.failMessage(t))
    }
}