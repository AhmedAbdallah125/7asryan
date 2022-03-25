package com.example.a7asryan.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.model.User
import com.example.a7asryan.repository.Repository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: Repository) : ViewModel() {

    private var _isExistence:MutableLiveData<Boolean> = MutableLiveData()
    val isExistence = _isExistence

    fun checkUserExistence(email: String, password: String) {
        viewModelScope.launch {
            val response = repository.checkUser(email, password)
            _isExistence.postValue(response)
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

}