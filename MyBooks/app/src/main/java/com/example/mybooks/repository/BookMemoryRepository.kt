package com.example.mybooks.repository

import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.factory.DatabaseRepositoryInterface

class BookMemoryRepository private constructor() : DatabaseRepositoryInterface {

    private val books = mutableListOf<BookEntity>()

    init {
        books.addAll(getInitialBooks())
    }

    companion object {
        private lateinit var instance : BookMemoryRepository

        fun getInstance(): BookMemoryRepository {
            synchronized(this) {
                if (!::instance.isInitialized){
                    instance = BookMemoryRepository()
                }
            }
            return instance
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

    override fun getAllBooks(): List<BookEntity> {
        return books
    }

    override fun getFavoriteBooks(): List<BookEntity> {
        return books.filter { it.favorite }
    }

    override fun getBookById(id: Int): BookEntity? {
        return books.find { it.id == id }
    }

    override fun delete(id: Int): Boolean {
        return books.removeIf { it.id == id }
    }

    override fun toggleFavoriteStatus(id: Int) {
        val book = books.find { it.id == id }
        if (book != null) {
            book.favorite = !book.favorite
        }
    }
}