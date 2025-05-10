package com.example.newsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapi.model.Articles
import java.util.Random


class NewsAdapter(var articles :List<Articles>, var onItemClick : (article :Articles)->Unit) : RecyclerView.Adapter<NewsAdapter.viewholder>(){
    private var lastPosition = -1

    inner class viewholder(itemview : View) :RecyclerView.ViewHolder(itemview){

        val title : TextView = itemview.findViewById(R.id.newstitle)
        val description : TextView = itemview.findViewById(R.id.description)
        val author : TextView = itemview.findViewById(R.id.author)
        val imageView  : ImageView = itemview.findViewById(R.id.imageUrl)
        fun bind(article: Articles) {
            title.text = article.title
            author.text = article.author
            description.text = article.description
            Glide.with(imageView.context)
                .load(article.urlToImage)
                .into(imageView)
            itemView.setOnClickListener{
                onItemClick(article)
            }
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val anim = ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            anim.duration =
                Random().nextInt(501).toLong() //to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim)
            lastPosition = position
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent,false)
        return viewholder(view)
     }


    override fun getItemCount(): Int {
        return articles.size
     }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val article = articles[position]
        setAnimation(holder.itemView, position = position)
        holder.bind(article)
    }
}