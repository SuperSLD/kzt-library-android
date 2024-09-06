package com.example.app_domain.models.documents

data class Request(
    val id: String,
    val title: String,
    val icon: String,
    val pages: List<RequestPage>
)

data class RequestPage(
    val id: String,
    val number: String,
    val lines: List<RequestPageLine>
)

data class RequestPageLine(
    val id: String,
    val type: String,
    val content: String,
    val required: Boolean,
    val number: Int,
)
