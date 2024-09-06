package com.example.app_domain.models.user

import java.io.Serializable

data class Achivment(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val done: Boolean,
): Serializable