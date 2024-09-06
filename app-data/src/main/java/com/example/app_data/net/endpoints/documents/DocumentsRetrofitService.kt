package com.example.app_data.net.endpoints.documents

import com.example.app_data.net.endpoints.DataWrapper
import com.example.app_domain.models.documents.DocumentsContent
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DocumentsRetrofitService {

    @GET("documents/all")
    suspend fun getAll(): DataWrapper<DocumentsContent>

    @POST("documents/sendRequest")
    suspend fun sendRequest(@Body sendRequest: SendRequest): DataWrapper<Int>
}
