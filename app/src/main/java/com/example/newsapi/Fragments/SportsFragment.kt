package com.example.newsapi.Fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.NewsAdapter
import com.example.newsapi.R
import com.example.newsapi.WebActivity
import com.example.newsapi.viewmodel.MainViewmodel
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.coroutines.launch


class SportsFragment :Fragment(R.layout.fragment_sports){
    private lateinit var recycler: RecyclerView
    private lateinit var adapter : NewsAdapter
    private lateinit var progress : ProgressDialog
    private lateinit var viewmodel: MainViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(MainViewmodel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.fetchNews("sports")
        }
        adapter = NewsAdapter(articles = mutableListOf()){article->
            val intent = Intent(requireContext(), WebActivity::class.java)
            intent.putExtra("weburl", article.url)
            startActivity(intent)
        }

        recycler = view.findViewById<RecyclerView>(R.id.sports_recycler).apply {
//            this.itemAnimator = SlideInLeftAnimator(OvershootInterpolator())
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(requireContext())

        }
        progress = ProgressDialog.show(context,"Loading, Please wait","News data fetching...",false,false)

        viewmodel.articles.observe(viewLifecycleOwner, Observer{ articles->
            adapter = NewsAdapter(articles){article->
                val intent = Intent(requireContext(), WebActivity::class.java)
                intent.putExtra("weburl", article.url)
                startActivity(intent)
            }
            recycler.adapter = adapter
            progress.dismiss()
        })


    }
}