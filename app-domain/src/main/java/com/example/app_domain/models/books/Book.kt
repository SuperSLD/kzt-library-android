package com.example.app_domain.models.books

data class Book(
    val id: String,
    // заголовок
    val title: String,
    // описание
    val description: String,
    // ссылка на обложку
    val cover: String,
    // рейтинг
    val rating: Float,
    // можно ли продлять книгу
    val renewal: Boolean,
    // автор книги
    val author: String,
    // дата сдачи, приходит только если книга на руках
    val date: String? = null

)