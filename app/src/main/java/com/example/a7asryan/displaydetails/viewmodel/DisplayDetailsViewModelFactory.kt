package com.example.a7asryan.displaydetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7asryan.repository.IRepository

class DisplayDetailsViewModelFactory(private val repository: IRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DisplayDetailsViewModel(repository) as T
    }
}