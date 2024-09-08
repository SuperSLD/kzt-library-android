package com.example.app_domain.datacontracts.net

import com.example.app_domain.models.books.BooksResponse

interface BookNetRepository {

    suspend fun getContent(): BooksResponse

    suspend fun orderBook(id: String)
    suspend fun renewBook(id: String)
}