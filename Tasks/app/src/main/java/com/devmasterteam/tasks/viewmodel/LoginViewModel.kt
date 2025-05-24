package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.viewmodel.helpers.ResponseHelper
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.viewmodel.helpers.AuthenticationHelper
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val authenticationHelper = AuthenticationHelper(application.applicationContext)
    private val personRepository = PersonRepository()
    private val priorityRepository = PriorityRepository(application.applicationContext)

    private val _loginResult = MutableLiveData<ValidationModel>()
    val loginResult: LiveData<ValidationModel> = _loginResult

    private val _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = personRepository.login(email, password)

            if (ResponseHelper.isSuccessfull(response)) {
                val personModel = response.body()!!

                RetrofitClient.addHeaders(personModel.token, personModel.personToken)

                authenticationHelper.saveUserData(personModel)
            }

            _loginResult.value = ResponseHelper.getResult(response)
        }
    }

    fun isUserLogged() {
        viewModelScope.launch {
            val isUserLogged = authenticationHelper.isUserLogged()

            if (isUserLogged) {
                val personModel = authenticationHelper.getUserData()
                RetrofitClient.addHeaders(personModel.token, personModel.personToken)

                val response = priorityRepository.getAll()
                if (response.isSuccessful){
                    val priorities = response.body()!!
                    priorityRepository.save(priorities)
                }

                _userLogged.value = true
            } else {
                _userLogged.value = false
            }
        }
    }
}