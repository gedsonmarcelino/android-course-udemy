package com.example.mybooks.entity

data class BookEntity(
    val id: Int,
    var title: String,
    var author: String,
    var favorite: Boolean,
    var genre: String
)