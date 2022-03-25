package com.example.a7asryan.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val userName: String,
    @PrimaryKey
    @NonNull
    val email: String,
    val password: String,
    val phoneNumber: String
)
