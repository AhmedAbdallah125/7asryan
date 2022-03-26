package com.example.a7asryan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.model.Article
import com.example.a7asryan.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: IRepository) : ViewModel() {

    private val _news: MutableLiveData<List<Article>> = MutableLiveData()
    val news: LiveData<List<Article>> = _news

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getNews().collect {

                it.let { i -> _news.postValue(i) }
            }
        }
    }

    fun updateArticale(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateFavoriteArticle(article)
        }
    }

}