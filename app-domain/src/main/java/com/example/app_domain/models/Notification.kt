package com.example.app_domain.models

data class Notification(
    val title: String,
    val text: String,
    val type: NotificationType,
    val bockId: String? = null,
    val eventId: String? = null,
)

enum class NotificationType {
    DEFAULT,
    EVENT,
    BOCK,
}