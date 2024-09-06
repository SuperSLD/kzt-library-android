package com.example.app_data.net.endpoints.user

import com.example.app_data.net.endpoints.DataWrapper
import com.example.app_domain.models.user.User
import com.example.data.net.endpoints.user.AuthResponse
import com.example.data.net.endpoints.user.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserRetrofitService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): DataWrapper<AuthResponse>

    @GET("auth/getInfo")
    suspend fun getInfo(): DataWrapper<User>
}
