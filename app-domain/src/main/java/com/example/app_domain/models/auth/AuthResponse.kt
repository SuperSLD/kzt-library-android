package com.example.app_domain.models.auth

data class AuthResponse(
    val token: String?,
    val registered: Boolean
)