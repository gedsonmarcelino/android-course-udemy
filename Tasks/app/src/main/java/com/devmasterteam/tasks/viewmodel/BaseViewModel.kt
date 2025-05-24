package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.exceptions.NoInternetException
import com.devmasterteam.tasks.service.model.ValidationModel

open class BaseViewModel(private val application: Application):  AndroidViewModel(application) {
    fun handleException(error: Exception): ValidationModel{
        if ( error is NoInternetException){
            return ValidationModel(false, application.getString(R.string.error_internet_connection))
        }

        return ValidationModel(false, application.getString(R.string.error_unexpected))
    }
}