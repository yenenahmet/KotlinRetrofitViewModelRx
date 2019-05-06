package com.yenen.ahmet.kotlinretrofitviewmodel.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Crypto {

    @SerializedName("ticker")
    @Expose
    var ticker: Ticker? = null
    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("error")
    @Expose
    var error: String? = null


    inner class Market {

        @SerializedName("market")
        @Expose
        var market: String? = null
        @SerializedName("price")
        @Expose
        var price: String? = null
        @SerializedName("volume")
        @Expose
        var volume: Float? = null

        var coinName: String? = null

    }

    inner class Ticker {

        @SerializedName("base")
        @Expose
        var base: String? = null
        @SerializedName("target")
        @Expose
        var target: String? = null
        @SerializedName("price")
        @Expose
        var price: String? = null
        @SerializedName("volume")
        @Expose
        var volume: String? = null
        @SerializedName("change")
        @Expose
        var change: String? = null
        @SerializedName("markets")
        @Expose
        var markets: List<Market>? = null

    }
}
