package com.example.app_domain.models.central

import java.io.Serializable

data class News(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val type: String,
    val date: String?
): Serializable

enum class NewsType(val type: String) {

    NEWS("news"),

    EVENT("event")
}
