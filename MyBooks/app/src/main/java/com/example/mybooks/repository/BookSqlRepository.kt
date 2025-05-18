package com.example.mybooks.repository

import android.content.ContentValues
import android.content.Context
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.database.BookDatabaseHelper
import com.example.mybooks.repository.factory.DatabaseRepositoryInterface
import com.example.mybooks.utils.AppConstants

class BookSqlRepository private constructor(context: Context) : DatabaseRepositoryInterface {

    private val database = BookDatabaseHelper(context)

    companion object {
        private lateinit var instance : BookSqlRepository

        fun getInstance(context: Context): BookSqlRepository {
            synchronized(this) {
                if (!::instance.isInitialized){
                    instance = BookSqlRepository(context)
                }
            }
            return instance
        }
    }

    private fun getBooks(selection:String?, selectionArgs: Array<String>?): List<BookEntity>{
        val books = mutableListOf<BookEntity>()

        val db = database.readableDatabase
        val cursor = db.query(AppConstants.DB_BOOK.TABLE_NAME,null, selection, selectionArgs,null,null,null)
        if ( cursor.moveToFirst()){
            do {
                val book = BookEntity(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(AppConstants.DB_BOOK.ID)),
                    author = cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.DB_BOOK.AUTHOR)),
                    genre = cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.DB_BOOK.GENRE)),
                    title = cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.DB_BOOK.TITLE)),
                    favorite = cursor.getInt(cursor.getColumnIndexOrThrow(AppConstants.DB_BOOK.FAVORITE)) == 1
                )
                books.add(book)
            } while(cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return books
    }

    override fun getAllBooks(): List<BookEntity> {
        return getBooks(null, null)
    }

    override fun getFavoriteBooks(): List<BookEntity> {
        val selection = "${AppConstants.DB_BOOK.FAVORITE} = ?"
        val selectionArgs = arrayOf("1")
        return getBooks(selection, selectionArgs)
    }

    override fun getBookById(id: Int): BookEntity? {
        val selection = "${AppConstants.DB_BOOK.ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val books = getBooks(selection, selectionArgs)
        return books.firstOrNull()
    }

    override fun delete(id: Int): Boolean {
        val book = getBookById(id)
        if (book != null) {
            val db = database.writableDatabase
            val where = "${AppConstants.DB_BOOK.ID} = ?"
            val whereArgs = arrayOf(book.id.toString())
            db.delete(AppConstants.DB_BOOK.TABLE_NAME, where, whereArgs)
            db.close()
            return true
        }
        return false
    }

    override fun toggleFavoriteStatus(id: Int) {
        val book = getBookById(id)
        if (book != null) {
            val db = database.writableDatabase

            val values = ContentValues().apply {
                put(AppConstants.DB_BOOK.FAVORITE, if (!book.favorite) 1 else 0 )
            }
            val where = "${AppConstants.DB_BOOK.ID} = ?"
            val whereArgs = arrayOf(book.id.toString())
            db.update(AppConstants.DB_BOOK.TABLE_NAME, values, where, whereArgs)

            db.close()
        }
    }
}