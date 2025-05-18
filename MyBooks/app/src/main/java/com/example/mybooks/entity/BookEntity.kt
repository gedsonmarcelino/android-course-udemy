package com.example.mybooks.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mybooks.utils.AppConstants

@Entity(tableName = "books")
class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean,

    @ColumnInfo(name = "genre")
    var genre: String
)