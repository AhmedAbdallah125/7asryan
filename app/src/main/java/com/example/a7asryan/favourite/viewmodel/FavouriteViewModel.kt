package com.example.a7asryan.favourite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.model.Article
import com.example.a7asryan.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavouriteViewModel(private val myRepo: IRepository) : ViewModel() {
    private val _favourites: MutableLiveData<List<Article>> = MutableLiveData()
    val favourites: LiveData<List<Article>> get() = _favourites
    fun getFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            myRepo.getFavouriteArticles().collect {
                it.let {
                    _favourites.postValue(it)
                }
            }
        }
    }

    fun removeFavourite(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            article.apply {
                isFavourite = false
            }
            myRepo.updateFavoriteArticle(article)
        }
    }
}