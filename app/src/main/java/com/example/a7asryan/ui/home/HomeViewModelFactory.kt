package com.example.a7asryan.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7asryan.repository.IRepository

class HomeViewModelFactory(private val iRepo: IRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(iRepo) as T
        } else {
            throw IllegalArgumentException("View Not Found")
        }
    }

}