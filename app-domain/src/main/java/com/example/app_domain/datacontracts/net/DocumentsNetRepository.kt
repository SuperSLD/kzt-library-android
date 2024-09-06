package com.example.app_domain.datacontracts.net

import com.example.app_domain.models.documents.DocumentsContent

interface DocumentsNetRepository {

    suspend fun getContent(): DocumentsContent

    suspend fun sendRequest(title: String)
}