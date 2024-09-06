package com.example.app_data.net.endpoints.central

import com.example.app_data.net.endpoints.DataWrapper
import com.example.app_domain.models.central.Central
import retrofit2.http.GET

interface CentralRetrofitService {

    @GET("news/central/getCentral")
    suspend fun get(): DataWrapper<Central>
}
