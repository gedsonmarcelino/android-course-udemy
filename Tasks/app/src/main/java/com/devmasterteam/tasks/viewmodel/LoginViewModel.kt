package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.viewmodel.helpers.ResponseHelper
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.viewmodel.helpers.AuthenticationHelper
import kotlinx.coroutines.launch

class LoginViewModel(private var application: Application) : AndroidViewModel(application) {

    private val authenticationHelper = AuthenticationHelper(application)
    private val personRepository = PersonRepository()

    private val _loginResult = MutableLiveData<ValidationModel>()
    val loginResult: LiveData<ValidationModel> = _loginResult

    private val _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = personRepository.login(email, password)

            if (ResponseHelper.isSuccessfull(response)) {
                val body = response.body()!!
                authenticationHelper.saveUserData(body)
            }

            _loginResult.value = ResponseHelper.getResult(response)
        }
    }

    fun isUserLogged() {
        viewModelScope.launch {
            _userLogged.value = authenticationHelper.isUserLogged()
        }
    }
}