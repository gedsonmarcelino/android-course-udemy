package com.devmasterteam.tasks.service.constants

/**
 * Constantes usadas na aplicação
 */
class TaskConstants private constructor() {

    // SharedPreferences
    object SHARED {
        const val TOKEN = "tokenkey"
        const val PERSON_KEY = "personkey"
        const val NAME = "personname"
    }

    // Requisições API
    object HEADER {
        const val TOKEN_KEY = "token"
        const val PERSON_KEY = "personkey"
    }

    object BUNDLE {
        const val TASKID = "taskid"
        const val TASKFILTER = "taskfilter"
    }

    // Filtro de tarefas
    object FILTER {
        const val ALL = 0
        const val NEXT = 1
        const val EXPIRED = 2
    }

    object RETROFIT {
        const val BASE_URL = "https://www.devmasterteam.com/CursoAndroidAPI/"
    }
}