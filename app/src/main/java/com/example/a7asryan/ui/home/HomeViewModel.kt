package com.example.a7asryan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.News
import com.example.a7asryan.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: IRepository) : ViewModel() {

    private val _news: MutableLiveData<List<Article>> = MutableLiveData()
    val news: LiveData<List<Article>> = _news

    fun getData() {
        viewModelScope.launch() {
            repo.getNews().collect {

                it.articles.let {i-> _news.postValue(i) }
            }
        }
    }
}