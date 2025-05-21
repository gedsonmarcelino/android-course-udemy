package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.repository.local.PreferencesManager
import com.devmasterteam.tasks.viewmodel.helpers.AuthenticationHelper
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationHelper = AuthenticationHelper(application)

    fun logout(){
        viewModelScope.launch {
            authenticationHelper.logout()
        }
    }
}