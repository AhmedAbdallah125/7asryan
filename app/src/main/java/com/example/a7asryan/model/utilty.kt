package com.example.a7asryan.model

import android.content.Context
import android.content.SharedPreferences
import com.example.a7asryan.R

private fun getSharedPreference(context: Context): SharedPreferences {
    return context.getSharedPreferences(
        context.getString(R.string.shared_pref),
        Context.MODE_PRIVATE
    )
}

fun isLogin(context: Context): Boolean {
    return getSharedPreference(context).getBoolean("isLogin", false)
}

fun updateLoginInSharedPreference(context: Context, isLogin: Boolean){
    getSharedPreference(context).edit().apply {
        putBoolean("isLogin", isLogin)
    }.apply()
}