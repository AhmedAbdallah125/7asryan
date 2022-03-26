package com.example.a7asryan.displaydetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.model.Article
import com.example.a7asryan.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayDetailsViewModel(private val repository: IRepository) : ViewModel() {
    private var _articleResponse: MutableLiveData<Article> = MutableLiveData()
    val articleResponse: LiveData<Article> = _articleResponse
    fun getArticle(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getArticleByUrl(url)
            _articleResponse.postValue(response)
        }
    }

    fun updateFavoriteArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteArticle(article)
        }
    }
}