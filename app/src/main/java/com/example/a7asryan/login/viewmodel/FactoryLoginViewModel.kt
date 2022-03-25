package com.example.a7asryan.login.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7asryan.repository.IRepository

class FactoryLoginViewModel(val myRpo: IRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(myRpo) as T
    }


}