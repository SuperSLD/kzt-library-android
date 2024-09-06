package com.example.app_domain.models.documents

data class Document(
    val title: String,
    val deadline: String,
    val needSign: Boolean = true,
)
