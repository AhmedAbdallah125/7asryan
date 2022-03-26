package com.example.a7asryan.login.view.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.repository.IRepository
import kotlinx.coroutines.launch

class LoginViewModel(val myRepo: IRepository) : ViewModel() {
    private val _response: MutableLiveData<LoginResult> = MutableLiveData()
    val loginResponse: LiveData<LoginResult> get() = _response


    fun login(email: String, password: String) {
        if (!isValidEmail(email)) {
            _response.value = LoginResult.InvalidResult(LoginError.EmailError)
        } else if (!isValidPassword(password)) {
            _response.value = LoginResult.InvalidResult(LoginError.PasswordError)
        } else {
            viewModelScope.launch {
                val response = myRepo.checkUser(email, password)
                var loginResult: LoginResult = LoginResult.LoginFailure
                if (response) loginResult = LoginResult.LoginSuccessful
                _response.value = loginResult
            }
        }


    }

    private fun isValidEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> false
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> false
            else -> true
        }
    }


    private fun isValidPassword(password: String): Boolean {
        return when {
            password.isEmpty() -> false
            password.length < 7 -> false
            else -> true

        }
    }


}

sealed class LoginResult {
    object LoginSuccessful : LoginResult()
    data class InvalidResult(val loginError: LoginError) : LoginResult()
    object LoginFailure : LoginResult()
}

enum class LoginError {
    EmailError,
    PasswordError,
}