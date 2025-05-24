package com.devmasterteam.tasks.service.exceptions

class NoInternetException(
    val errorMessage: String = "Internet connection not available"
) :
    Exception(errorMessage)