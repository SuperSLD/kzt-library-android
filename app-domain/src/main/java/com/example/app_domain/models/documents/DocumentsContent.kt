package com.example.app_domain.models.documents

data class DocumentsContent(
    val documents: List<Document>,
    val requests: List<Request>,
)
