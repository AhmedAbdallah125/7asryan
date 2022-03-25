package com.example.a7asryan.model

import androidx.room.Entity

//dummy class
@Entity(tableName = "user")
data class User(
    private val userName: String,
    private val email: String,
    private val password: String,
    private val phoneNumber: String
)
