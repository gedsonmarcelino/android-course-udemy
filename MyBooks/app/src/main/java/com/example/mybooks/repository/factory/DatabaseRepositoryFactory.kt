package com.example.mybooks.repository.factory

import android.content.Context
import com.example.mybooks.repository.BookMemoryRepository
import com.example.mybooks.repository.BookSqlRepository
import com.example.mybooks.utils.AppConstants

class DatabaseRepositoryFactory {
    companion object {
        fun getInstance(context: Context): DatabaseRepositoryInterface {
            if (AppConstants.DB.FACTORY == "sql") {
                return BookSqlRepository.getInstance(context)
            }

            return BookMemoryRepository.getInstance()
        }
    }
}