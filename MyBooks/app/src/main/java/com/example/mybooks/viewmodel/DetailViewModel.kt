package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookSqlRepository
import com.example.mybooks.repository.factory.DatabaseRepositoryFactory

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DatabaseRepositoryFactory.getInstance(application.applicationContext)

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