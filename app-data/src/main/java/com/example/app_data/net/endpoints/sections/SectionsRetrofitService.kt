package com.example.app_data.net.endpoints.sections

import com.example.app_data.net.endpoints.DataWrapper
import com.example.app_domain.models.auth.AuthResponse
import com.example.app_domain.models.sections.Section
import com.example.app_domain.models.user.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SectionsRetrofitService {

    @GET("sections/get")
    suspend fun get(): DataWrapper<List<Section>>
}
