package com.example.app_domain.models.user

import com.example.app_domain.models.Notification

data class User(
    val id: String,
    val name: String,
    val lastname: String,
    val midname: String,
    val companyRole: String,
    val coins: String,
    val avatar: String,
    val achivements: List<Achivment>,
    val notifications: List<Notification>,
) {

    fun nameString() = "$lastname $name $midname"
}