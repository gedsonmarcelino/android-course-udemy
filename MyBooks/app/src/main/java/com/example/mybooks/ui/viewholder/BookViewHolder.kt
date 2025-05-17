package com.example.mybooks.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mybooks.R
import com.example.mybooks.databinding.ItemBookBinding
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.ui.listener.BookListener

class BookViewHolder(
    private val item: ItemBookBinding,
    private val listener: BookListener
) :
    ViewHolder(item.root) {

    fun bind(book: BookEntity) {
        item.txtTitle.text = book.title
        item.txtGenre.text = book.genre
        item.txtAuthor.text = book.author

        setGenreBackground(book)
        setFavoriteBackground(book)

        item.txtTitle.setOnClickListener { listener.onClick(book.id) }
        item.imgFavorite.setOnClickListener{ listener.toggleFavoriteStatus(book.id) }
    }

    private fun setGenreBackground(book: BookEntity) {
        when (book.genre) {
            "Terror" -> {
                item.txtGenre.setBackgroundResource(R.drawable.rounded_label_red)
            }

            "Fantasia" -> {
                item.txtGenre.setBackgroundResource(R.drawable.rounded_label_fantasy)
            }

            else -> {
                item.txtGenre.setBackgroundResource(R.drawable.rounded_label_tea)
            }
        }
    }

    private fun setFavoriteBackground(book: BookEntity) {
        if (book.favorite) {
            item.imgFavorite.setBackgroundResource(R.drawable.ic_favorite)
        } else {
            item.imgFavorite.setBackgroundResource(R.drawable.ic_favorite_empty)
        }
    }
}