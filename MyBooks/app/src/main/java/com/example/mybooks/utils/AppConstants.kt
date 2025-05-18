package com.example.mybooks.utils

class AppConstants private constructor(){

    object LAYOUT {
        var PADDING_START = 20
        var PADDING_END = 20
    }

    object BOOK {
        var ID:String = "book_id"
    }

    object DB {
        var FACTORY = "sql" // memory | sql | room
        var NAME = "my_books_database"
        var VERSION = 2
    }

    object DB_BOOK {
        var TABLE_NAME = "books"
        var ID = "id"
        var TITLE = "title"
        var AUTHOR = "author"
        var GENRE = "genre"
        var FAVORITE = "favorite"

    }
}