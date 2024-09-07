package com.example.app_data.net.endpoints.user

import com.example.app_data.net.endpoints.DataWrapper
import com.example.app_domain.models.auth.AuthResponse
import com.example.app_domain.models.user.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserRetrofitService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): DataWrapper<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegistrationRequest): DataWrapper<AuthResponse>

    @GET("auth/getInfo")
    suspend fun getInfo(): DataWrapper<User>
}
