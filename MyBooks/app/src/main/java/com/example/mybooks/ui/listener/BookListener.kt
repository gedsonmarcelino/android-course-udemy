package com.example.mybooks.ui.listener

interface BookListener {
    fun onClick(id:Int)
    fun toggleFavoriteStatus(id:Int)
}