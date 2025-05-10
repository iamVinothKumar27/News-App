package com.example.newsapi

import com.example.newsapi.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Retrofitservice {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country :String,
        @Query("category") category :String,
        @Query("apiKey") apiKey :String,
    ): Response<NewsData>
}