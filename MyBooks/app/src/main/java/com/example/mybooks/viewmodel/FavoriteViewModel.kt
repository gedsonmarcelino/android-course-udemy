package com.example.mybooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository

class FavoriteViewModel : ViewModel() {

    private var _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    private val repository = BookRepository.getInstance()

    fun getAllBooks(){
        _books.value = repository.getFavoriteBooks()
    }

    fun toggleFavoriteStatus(id: Int){
        repository.toggleFavoriteStatus(id)
    }
}