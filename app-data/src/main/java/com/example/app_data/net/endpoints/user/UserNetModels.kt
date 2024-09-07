package com.example.app_data.net.endpoints.user

data class LoginRequest(
    val login: String,
)

data class RegistrationRequest(
    val login: String,
    val name: String,
    val lastName: String,
    val midName: String,
    val avatar: String?
)