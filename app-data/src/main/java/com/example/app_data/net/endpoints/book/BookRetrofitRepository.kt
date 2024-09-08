package com.example.app_data.net.endpoints.book

import com.example.app_domain.datacontracts.net.BookNetRepository
import com.example.app_domain.models.books.BooksResponse
import retrofit2.Retrofit

class BookRetrofitRepository(
    val retrofit: Retrofit
): BookNetRepository {

    private val service by lazy { retrofit.create(BookRetrofitService::class.java) }

    override suspend fun getContent(): BooksResponse {
        return service.getAll().dataOrThrow()!!
    }

    override suspend fun orderBook(id: String) {
        service.orderBook(id).dataOrThrow()
    }

    override suspend fun renewBook(id: String) {
        service.renewBook(id).dataOrThrow()
    }
}