package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import retrofit2.Response

class PersonRepository(context: Context): BaseRespository(context) {

    private val remote = RetrofitClient.getService(PersonService::class.java)

    suspend fun login(email: String, password: String): Response<PersonModel> {
        return safeApiCall {
            remote.login(email, password)
        }
    }

    suspend fun create(name: String, email: String, password: String): Response<PersonModel> {
        return safeApiCall {
            remote.create(name, email, password, "false")
        }
    }
}