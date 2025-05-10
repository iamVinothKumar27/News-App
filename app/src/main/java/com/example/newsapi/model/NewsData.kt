package com.example.newsapi.model

data class NewsData(
    val status :String ?="",
    val totalResults : Int =0,
    val articles : List<Articles>?=null
)
