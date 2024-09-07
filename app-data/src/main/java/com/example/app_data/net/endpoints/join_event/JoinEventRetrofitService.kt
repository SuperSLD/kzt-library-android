package com.example.app_data.net.endpoints.join_event

import com.example.app_data.net.endpoints.DataWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface JoinEventRetrofitService {
    @GET("news/joinEvent/{id}")
    suspend fun joinEvent(@Path("id") id: String): DataWrapper<Int>
}