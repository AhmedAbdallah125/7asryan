package com.example.a7asryan.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a7asryan.R
import com.example.a7asryan.databinding.HomeNewsRowBinding
import com.example.a7asryan.model.Article

class HomeNewsAdapter(var newsList: List<Article>,val context:Context) :
    RecyclerView.Adapter<HomeNewsAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: HomeNewsRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HomeNewsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.author.text= newsList[position].author
        holder.binding.date.text = newsList[position].publishedAt
        holder.binding.title.text = newsList[position].title
        Glide.with(context).load(newsList[position].urlToImage).into(holder.binding.newsImg)

        holder.binding.favIc.setOnClickListener(View.OnClickListener {
            if(newsList[position].isFavourite){
                newsList[position].isFavourite = false
                holder.binding.favIc.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }else{
                newsList[position].isFavourite = true
                holder.binding.favIc.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        })
    }

    override fun getItemCount(): Int {
       return newsList.size
    }

}