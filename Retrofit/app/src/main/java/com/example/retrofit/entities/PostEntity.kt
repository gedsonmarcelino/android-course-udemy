package com.example.retrofit.entities

import com.google.gson.annotations.SerializedName

data class PostEntity(

    @SerializedName("userId")
    var userId: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("body")
    var body: String
)