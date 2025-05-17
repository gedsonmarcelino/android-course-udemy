package com.example.mybooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository

class DetailViewModel : ViewModel() {
    private val repository = BookRepository.getInstance()

    private var _book = MutableLiveData<BookEntity>()
    val book: LiveData<BookEntity> = _book

    private var _bookIsRemoved = MutableLiveData<Boolean>()
    val bookIsRemoved: LiveData<Boolean> = _bookIsRemoved

    fun getBookById(id: Int) {
        _book.value = repository.getBookById(id)
    }

    fun delete(id: Int) {
        _bookIsRemoved.value = repository.delete(id)
    }

    fun toggleFavoriteStatus(id: Int) {
        repository.toggleFavoriteStatus(id)
    }
}