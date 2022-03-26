package com.example.a7asryan.favourite.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7asryan.databinding.CardFavouriteBinding

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: CardFavouriteBinding
    ) :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.ViewHolder {
        return ViewHolder(
            CardFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}