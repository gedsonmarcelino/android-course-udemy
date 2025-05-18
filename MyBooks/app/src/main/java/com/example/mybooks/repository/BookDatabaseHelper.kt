package com.example.mybooks.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.utils.AppConstants

class BookDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, AppConstants.DB.NAME, null, AppConstants.DB.VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_BOOKS)
        insertBooks(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        insertBooks(db)
    }

    companion object {
        var CREATE_TABLE_BOOKS = """ 
            CREATE TABLE ${AppConstants.DB_BOOK.TABLE_NAME} (
                ${AppConstants.DB_BOOK.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${AppConstants.DB_BOOK.TITLE} TEXT NOT NULL,
                ${AppConstants.DB_BOOK.AUTHOR} TEXT NOT NULL,
                ${AppConstants.DB_BOOK.GENRE} TEXT NOT NULL,
                ${AppConstants.DB_BOOK.FAVORITE} BOOLEAN DEFAULT 0
            );
        """.trimIndent()
    }

    private fun insertBooks(db:SQLiteDatabase){
        val books = getInitialBooks()
        for (book in books){
            val values = ContentValues().apply {
                put(AppConstants.DB_BOOK.TITLE, book.title)
                put(AppConstants.DB_BOOK.AUTHOR, book.author)
                put(AppConstants.DB_BOOK.GENRE, book.genre)
                put(AppConstants.DB_BOOK.FAVORITE, if (book.favorite) 1 else 0 )
            }
            db.insert(AppConstants.DB_BOOK.TABLE_NAME, null, values)
        }
    }

    private fun getInitialBooks(): List<BookEntity> {
        return listOf(
            BookEntity(1, "To Kill a Mockingbird", "Harper Lee", true, "Ficção"),
            BookEntity(2, "Dom Casmurro", "Machado de Assis", false, "Romance"),
            BookEntity(3, "O Hobbit", "J.R.R. Tolkien", true, "Fantasia"),
            BookEntity(4, "Cem Anos de Solidão", "Gabriel García Márquez", false, "Romance"),
            BookEntity(5, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", false, "Fantasia"),
            BookEntity(6, "Crime e Castigo", "Fiódor Dostoiévski", false, "Ficção policial"),
            BookEntity(7, "Frankenstein", "Mary Shelley", false, "Terror"),
            BookEntity(8, "Harry Potter e a Pedra Filosofal", "J.K. Rowling", false, "Fantasia"),
            BookEntity(9, "Neuromancer", "William Gibson", false, "Cyberpunk"),
            BookEntity(10, "Senhor dos Anéis", "J.R.R. Tolkien", false, "Fantasia"),
            BookEntity(11, "Drácula", "Bram Stoker", false, "Terror"),
            BookEntity(12, "Orgulho e Preconceito", "Jane Austen", false, "Romance"),
            BookEntity(13, "Harry Potter e a Câmara Secreta", "J.K. Rowling", false, "Fantasia"),
            BookEntity(14, "As Crônicas de Nárnia", "C.S. Lewis", false, "Fantasia"),
            BookEntity(15, "O Código Da Vinci", "Dan Brown", false, "Mistério"),
            BookEntity(16, "It: A Coisa", "Stephen King", false, "Terror"),
            BookEntity(17, "Moby Dick", "Herman Melville", true, "Aventura"),
            BookEntity(18, "O Nome do Vento", "Patrick Rothfuss", true, "Fantasia"),
            BookEntity(19, "O Conde de Monte Cristo", "Alexandre Dumas", true, "Aventura"),
            BookEntity(20, "Os Miseráveis", "Victor Hugo", false, "Romance")
        )
    }
}