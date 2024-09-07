package com.example.app_data.net.endpoints.user

data class LoginRequest(
    val login: String,
)

data class RegistrationRequest(
    val login: String,
    val name: String,
    val lastname: String,
    val midname: String,
    val avatar: String?
)