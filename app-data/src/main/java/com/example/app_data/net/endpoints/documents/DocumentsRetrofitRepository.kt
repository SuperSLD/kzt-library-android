package com.example.app_data.net.endpoints.documents

import com.example.app_domain.datacontracts.net.DocumentsNetRepository
import com.example.app_domain.models.documents.DocumentsContent
import retrofit2.Retrofit

class DocumentsRetrofitRepository(
    retrofit: Retrofit,
) : DocumentsNetRepository {

    private val service by lazy { retrofit.create(DocumentsRetrofitService::class.java) }

    override suspend fun getContent(): DocumentsContent {
        return service.getAll().dataOrThrow()!!
    }

    override suspend fun sendRequest(title: String) {
        service.sendRequest(SendRequest(title))
    }
}
