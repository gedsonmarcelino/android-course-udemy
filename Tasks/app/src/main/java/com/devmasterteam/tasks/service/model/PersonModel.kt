package com.devmasterteam.tasks.service.model

import com.google.gson.annotations.SerializedName

data class PersonModel (
    @SerializedName("token")
    val token: String,

    @SerializedName("personToken")
    val personToken: String,

    @SerializedName("name")
    val name: String
)