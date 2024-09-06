package com.example.data.net.endpoints.user

data class LoginRequest(
    val login: String,
)

data class AuthResponse(
    val token: String,
)