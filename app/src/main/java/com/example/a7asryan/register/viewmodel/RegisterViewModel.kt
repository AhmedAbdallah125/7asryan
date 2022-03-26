package com.example.a7asryan.register.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7asryan.model.User
import com.example.a7asryan.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: Repository) : ViewModel() {

    private val _registerResult: MutableLiveData<RegisterResult> = MutableLiveData()
    val registerResult: LiveData<RegisterResult>
        get() = _registerResult

    fun registerUser(email: String, password: String, userName: String, phoneNumber: String) {
        when {
            isEmailInvalid(email) -> {
                _registerResult.value = RegisterResult.InvalidData(ErrorType.EmailError)
            }
            isPasswordInvalid(password) -> {
                _registerResult.value = RegisterResult.InvalidData(ErrorType.PasswordError)
            }
            isUserNameInvalid(userName) -> {
                _registerResult.value = RegisterResult.InvalidData(ErrorType.UserNameError)
            }
            isPhoneNumberInvalid(phoneNumber) -> {
                _registerResult.value = RegisterResult.InvalidData(ErrorType.PhoneNumberError)
            }
            else -> {
                checkUserExistence(User(userName, email, password, phoneNumber))
            }
        }
    }

    private fun checkUserExistence(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.checkUser(user.email, user.password)
            if (response) {
                _registerResult.value = RegisterResult.RegisterError
            } else {
                insertUser(user)
                _registerResult.value = RegisterResult.RegisterSuccessful
            }
        }
    }

    private suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }

    private fun isEmailInvalid(email: String): Boolean {
        return when {
            email.isEmpty() -> true
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> true
            else -> false
        }
    }

    private fun isPasswordInvalid(password: String): Boolean {
        return when {
            password.isEmpty() -> true
            password.length < 7 -> true
            else -> false
        }
    }

    private fun isPhoneNumberInvalid(phoneNumber: String): Boolean {
        return when {
            phoneNumber.isEmpty() -> true
            !phoneNumber.matches(Regex("(01)[0-9]{9}")) -> true
            else -> false
        }
    }

    private fun isUserNameInvalid(userName: String): Boolean {
        return when {
            userName.isEmpty() -> true
            else -> false
        }
    }
}

sealed class RegisterResult {
    object RegisterSuccessful : RegisterResult()
    object RegisterError : RegisterResult()
    data class InvalidData(val error: ErrorType) : RegisterResult()
}

enum class ErrorType {
    EmailError,
    PasswordError,
    PhoneNumberError,
    UserNameError
}