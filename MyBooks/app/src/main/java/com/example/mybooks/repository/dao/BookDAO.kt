package com.example.mybooks.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mybooks.entity.BookEntity

@Dao
interface BookDAO {

    @Query("SELECT * FROM books")
    fun getAllBooks():List<BookEntity>

    @Query("SELECT * FROM books WHERE favorite = 1")
    fun getFavoriteBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: Int): BookEntity?

    @Insert
    fun insert(books: List<BookEntity>)

    @Update
    fun update(book: BookEntity)

    @Delete
    fun delete(book: BookEntity) : Int
}