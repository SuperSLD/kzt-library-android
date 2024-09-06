package com.example.app_ui.screens.main.profile.achivments

import com.example.app_domain.models.user.Achivment
import com.example.app_ui.common.AppScreen

class AchivmentsScreen(
    val achivments: List<Achivment>
) : AppScreen() {

    override fun getFragment() = AchivmentsFragment.newInstance(this)
}