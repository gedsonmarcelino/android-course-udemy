package com.example.mybooks.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.dao.BookDAO
import com.example.mybooks.utils.AppConstants

@Database(entities = [BookEntity::class], version = 1)
abstract class BookRoomDatabaseHelper : RoomDatabase() {

    abstract fun bookDAO(): BookDAO

    companion object {
        private lateinit var instance: BookRoomDatabaseHelper

        fun getDatabase(context: Context): BookRoomDatabaseHelper {
            if (!::instance.isInitialized) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        BookRoomDatabaseHelper::class.java,
                        "books_db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}