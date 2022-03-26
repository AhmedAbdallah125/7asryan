package com.example.a7asryan.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7asryan.repository.Repository

class FactoryFavouriteViewModel(private val myRepository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(myRepository) as T
    }
}