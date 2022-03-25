package com.example.a7asryan.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    private val userName: String,
    @PrimaryKey
    @NonNull
    private val email: String,
    private val password: String,
    private val phoneNumber: String
)
