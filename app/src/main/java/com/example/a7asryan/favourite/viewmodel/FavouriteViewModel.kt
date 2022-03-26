package com.example.a7asryan.favourite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.model.Article
import com.example.a7asryan.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavouriteViewModel(private val myRepo: IRepository) : ViewModel() {
    private val _favourites: MutableLiveData<FavouriteResult> = MutableLiveData()
    val favourites: LiveData<FavouriteResult> get() = _favourites
    fun getFavourites() {
        _favourites.postValue(FavouriteResult.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            myRepo.getFavouriteArticles().collect {

                    if (it.isEmpty()) {
                        _favourites.postValue(FavouriteResult.Empty)
                    } else
                        _favourites.postValue(FavouriteResult.Success(it))

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

sealed class FavouriteResult {
    object Loading : FavouriteResult()
    object ErrorResult : FavouriteResult()
    object Empty : FavouriteResult()
    data class Success(val articles: List<Article>) : FavouriteResult()

}