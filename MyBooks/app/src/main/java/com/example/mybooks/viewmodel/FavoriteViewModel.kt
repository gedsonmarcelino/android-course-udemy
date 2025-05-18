package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookSqlRepository
import com.example.mybooks.repository.factory.DatabaseRepositoryFactory

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    private val repository = DatabaseRepositoryFactory.getInstance(application.applicationContext)

    fun getAllBooks(){
        _books.value = repository.getFavoriteBooks()
    }

    fun toggleFavoriteStatus(id: Int){
        repository.toggleFavoriteStatus(id)
    }
}