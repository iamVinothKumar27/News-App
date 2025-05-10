package com.example.newsapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.newsapi.MainActivity
import com.example.newsapi.RetrofitHelper
import com.example.newsapi.Retrofitservice
import com.example.newsapi.model.Articles
import kotlinx.coroutines.launch

class MainViewmodel : ViewModel() {
    val service = RetrofitHelper.getInstance().create(Retrofitservice::class.java)
    val _articles = MutableLiveData<List<Articles>>()
    var articles: LiveData<List<Articles>> = _articles

    suspend fun fetchNews(category :String){
            val response = service.getNews("us",category, apiKey = MainActivity.APIKEY)
            if(response.isSuccessful){
                val data = response.body()?.articles?.map {
                    Articles(
                        author = it.author,
                        title = it.title,
                        description = it.description,
                        url = it.url,
                        urlToImage = it.urlToImage
                    )
                }?: emptyList()

                 _articles.postValue(data)

            }
            else{
                Log.d("pathri",response.toString())
            }

    }
}