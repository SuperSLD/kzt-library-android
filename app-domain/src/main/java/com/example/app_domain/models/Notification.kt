package com.example.app_domain.models

import com.example.app_domain.models.central.News

data class Notification(
    val title: String,
    val text: String,
    val type: String,
    val event: News? = null,
)