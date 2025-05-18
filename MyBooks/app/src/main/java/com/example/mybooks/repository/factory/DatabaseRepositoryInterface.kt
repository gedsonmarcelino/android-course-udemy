package com.example.mybooks.repository.factory

import com.example.mybooks.entity.BookEntity

interface DatabaseRepositoryInterface {

    fun getAllBooks(): List<BookEntity>

    fun getFavoriteBooks(): List<BookEntity>

    fun getBookById(id: Int): BookEntity?

    fun delete(id: Int): Boolean

    fun toggleFavoriteStatus(id: Int)
}