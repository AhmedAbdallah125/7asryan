package com.example.a7asryan.login.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.repository.IRepository
import kotlinx.coroutines.launch

class LoginViewModel(val myRepo: IRepository) : ViewModel() {
    private var _checkResponse: MutableLiveData<Boolean> = MutableLiveData()
    var checkResponse = _checkResponse
    fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            val response = myRepo.checkUser(email, password)
            _checkResponse.postValue(response)
        }
    }

}