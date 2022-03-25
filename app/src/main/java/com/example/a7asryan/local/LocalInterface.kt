package com.example.a7asryan.local

import com.example.a7asryan.model.User

interface LocalInterface {
    suspend fun insertUser(user: User)
    suspend fun checkUserExistence(email: String, password: String): Boolean

}