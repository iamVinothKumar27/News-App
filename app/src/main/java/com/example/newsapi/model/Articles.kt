package com.example.newsapi.model

data class Articles(
    val author :String ?="",
    val title : String ?="",
    val description :String?="",
    val url : String?="",
    val urlToImage :String?=""
)
