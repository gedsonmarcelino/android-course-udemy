package com.devmasterteam.tasks.viewmodel.helpers

import com.devmasterteam.tasks.service.model.ValidationModel
import com.google.gson.Gson
import retrofit2.Response

class ResponseHelper private constructor() {

    companion object {
        fun <T> getResult(response: Response<T>): ValidationModel {
            return if (isSuccessfull(response)) getSuccess() else getError(response)
        }

        fun <T> isSuccessfull(response: Response<T>): Boolean {
            return (response.isSuccessful && response.body() != null)
        }

        private fun getSuccess(): ValidationModel {
            return ValidationModel(true, null)
        }

        private fun <T> getError(response: Response<T>): ValidationModel {
            val messageJSON = response.errorBody()?.string().toString()
            val message = Gson().fromJson(messageJSON, String::class.java)
            return ValidationModel(false, message)
        }
    }


}