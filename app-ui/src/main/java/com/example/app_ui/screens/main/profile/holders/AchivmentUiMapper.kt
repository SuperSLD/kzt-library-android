package com.example.app_ui.screens.main.profile.holders

import com.example.app_ui.R

class AchivmentUiMapper {

    fun toOnIconRes(icon: String) = when(icon) {
        "champion" -> R.drawable.ic_on_champion
        "education" -> R.drawable.ic_on_education
        "authorisation" -> R.drawable.ic_on_authorisation
        "shop" -> R.drawable.ic_on_shop
        "storage" -> R.drawable.ic_on_storage
        "photo" -> R.drawable.ic_on_photo
        "heart" -> R.drawable.ic_on_heart
        "chair" -> R.drawable.ic_on_chair
        "voice" -> R.drawable.ic_on_voice
        "vactination" -> R.drawable.ic_on_vactination
        "chart_top" -> R.drawable.ic_on_chart_top
        else -> R.drawable.ic_lock
    }

    fun toOffIconRes(icon: String) = when(icon) {
        "champion" -> R.drawable.ic_off_champion
        "education" -> R.drawable.ic_off_education
        "authorisation" -> R.drawable.ic_off_authorisation
        "shop" -> R.drawable.ic_off_shop
        "storage" -> R.drawable.ic_off_storage
        "photo" -> R.drawable.ic_off_photo
        "heart" -> R.drawable.ic_off_heart
        "chair" -> R.drawable.ic_off_chair
        "voice" -> R.drawable.ic_off_voice
        "vactination" -> R.drawable.ic_off_vactination
        "chart_top" -> R.drawable.ic_off_chart_top
        else -> R.drawable.ic_lock
    }
}