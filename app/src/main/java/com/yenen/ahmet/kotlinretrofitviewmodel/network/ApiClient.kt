package com.yenen.ahmet.kotlinretrofitviewmodel.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiClient {
    companion object {
        @Volatile
        private var retrofit: Retrofit? = null

        fun <S> createService(sClass: Class<S>): S {
            retrofit ?: synchronized(this) {
                retrofit ?: Retrofit.Builder()
                    .baseUrl("https://api.cryptonator.com/api/full/")
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().also { retrofit = it }
            }
            return retrofit!!.create(sClass)
        }

        private fun getGson(): Gson {
            return GsonBuilder()
                .setLenient()
                .create()
        }

        // Error Handler Message // gelen hata mesajına göre handler edilip geriye ona göre mesaj yollanabilir !
        fun failMessage(t: Throwable): String {
            Log.e("ApiClientFailMessage", t.toString())
            return if (t is UnknownHostException || t is IOException) {
                "İnternet Bağlantınızda Sorun Var ! Bağlantı Kurulamadı "
            } else if (t is JsonSyntaxException) {
                "Yapısal Bir Sorun Oluştu"
            } else if (t is TimeoutException) {
                "Bağlantı Zaman Aşımına Uğradı"
            }else if(t is HttpException){
                val response = t.response()
                Log.e("HttpExCode =",response.code().toString())
                "Bağlantı Sorunu !"
            } else{
                "Veriler alınırken hata oluştu ! "
            }
        }
        fun <T> succesControl(response:Response<T>?):Boolean{
            return response!=null && response.isSuccessful && response.body()!=null
        }
    }
}