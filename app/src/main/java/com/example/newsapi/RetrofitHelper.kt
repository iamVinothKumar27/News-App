package com.example.newsapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val baseUrl  = "https://newsapi.org/"
    val client  = OkHttpClient.Builder()
        .addInterceptor{chain->
            val req = chain.request().newBuilder()
                .addHeader("User-Agent", "newsAPI")
                .build()
            chain.proceed(req)
        }
        .build()
    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}