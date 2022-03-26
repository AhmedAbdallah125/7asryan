package com.example.a7asryan.favourite.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7asryan.databinding.CardFavouriteBinding
import com.example.a7asryan.model.Article

class FavouriteAdapter(
    private val onDelete: (Article) -> Unit,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {


    private var articles: List<Article> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setFavouriteList(articleList: List<Article>) {
        articles = articleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.ViewHolder {
        return ViewHolder(
            CardFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: FavouriteAdapter.ViewHolder, position: Int) {
        articles[position].title.let {
            holder.binding.txtFavourite.text = it ?: ""
        }
        holder.binding.favCardLayout.setOnClickListener {
            onClick(articles[position].url)
        }
        holder.binding.deleteFavourite.setOnClickListener {
            onDelete(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ViewHolder(
        val binding: CardFavouriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}