package com.example.app_data.net.endpoints.book

import com.example.app_data.net.endpoints.DataWrapper
import com.example.app_domain.models.books.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BookRetrofitService {

    @GET("books/list")
    suspend fun getAll(): DataWrapper<BooksResponse>

    @GET("books/add/{id}")
    suspend fun orderBook(@Path("id") id: String): DataWrapper<Int>

    @GET("books/renew/{id}")
    suspend fun renewBook(@Path("id") id: String): DataWrapper<Int>
}