package com.example.a7asryan.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a7asryan.R
import com.example.a7asryan.databinding.HomeNewsRowBinding
import com.example.a7asryan.model.Article


class HomeNewsAdapter(
    var newsList: List<Article>,
    val context: Context,
    private val onClick: (Article) -> Unit,
    private val onCardClick: (String) -> Unit
) :
    RecyclerView.Adapter<HomeNewsAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: HomeNewsRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HomeNewsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = newsList[position]
        holder.binding.homeCard.setOnClickListener(View.OnClickListener {
           onCardClick(article.url)
        })
        holder.binding.author.text = article.author
        holder.binding.date.text = article.publishedAt
        holder.binding.title.text = article.title

        Glide.with(context).load(article.urlToImage).into(holder.binding.newsImg)

        holder.binding.favIc.setImageResource(getFavouriteImageResourceId(article.isFavourite))
        holder.binding.favIc.setOnClickListener(View.OnClickListener {
            article.isFavourite = !article.isFavourite
            holder.binding.favIc.setImageResource(getFavouriteImageResourceId(article.isFavourite))
            onClick(article)
        })
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun getFavouriteImageResourceId(fav: Boolean): Int {
        return if (fav) {
            R.drawable.ic_baseline_favorite_24
        } else {
            R.drawable.ic_baseline_favorite_border_24
        }
    }


}