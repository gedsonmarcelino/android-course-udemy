package com.example.motivation.helpers

import android.content.Context
import com.example.motivation.utils.Constants
import androidx.core.content.edit

class LocalStorage (context: Context) {

    private val shared = context.getSharedPreferences(Constants.LOCAL_STORAGE_KEY, Context.MODE_PRIVATE)

    fun set(key:String, value:String){
        shared.edit { putString(key, value) }
    }

    fun get(key: String): String{
        return shared.getString(key, "") ?: ""
    }
}