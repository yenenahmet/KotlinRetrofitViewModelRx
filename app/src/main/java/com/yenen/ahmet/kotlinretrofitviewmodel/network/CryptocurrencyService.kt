package com.yenen.ahmet.kotlinretrofitviewmodel.network

import com.yenen.ahmet.kotlinretrofitviewmodel.model.Crypto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptocurrencyService {

    @GET("{coin}-usd")
    fun getCoinData(@Path("coin") coin: String): Observable<Crypto>
}