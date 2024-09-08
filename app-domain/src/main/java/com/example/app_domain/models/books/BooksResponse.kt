package com.example.app_domain.models.books

data class BooksResponse(
    val myBook: List<Book>,
    val rec: List<Book>,
    val new: List<Book>
)
