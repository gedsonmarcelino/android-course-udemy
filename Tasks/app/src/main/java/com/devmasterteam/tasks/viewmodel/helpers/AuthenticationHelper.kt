package com.devmasterteam.tasks.viewmodel.helpers

import android.app.Application
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.repository.local.PreferencesManager

class AuthenticationHelper(application: Application) {

    private val preferencesManager = PreferencesManager(application.applicationContext)

    suspend fun saveUserData(person: PersonModel) {
        preferencesManager.store(
            TaskConstants.SHARED.PERSON_KEY, person.personToken
        )
        preferencesManager.store(
            TaskConstants.SHARED.NAME, person.name
        )
        preferencesManager.store(
            TaskConstants.SHARED.TOKEN, person.token
        )
    }

    suspend fun getUserData(): PersonModel {
        val token = preferencesManager.get(
            TaskConstants.SHARED.TOKEN
        )
        val name = preferencesManager.get(
            TaskConstants.SHARED.NAME
        )
        val personKey = preferencesManager.get(
            TaskConstants.SHARED.PERSON_KEY
        )

        return PersonModel(token, personKey, name)
    }

    suspend fun isUserLogged(): Boolean {
        return preferencesManager.get(
            TaskConstants.SHARED.TOKEN
        ) != ""
    }

    suspend fun logout() {
        preferencesManager.remove(
            TaskConstants.SHARED.TOKEN
        )
        preferencesManager.remove(
            TaskConstants.SHARED.NAME
        )
        preferencesManager.remove(
            TaskConstants.SHARED.PERSON_KEY
        )
    }
}