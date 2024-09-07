package com.example.app_ui.screens.detail_news

import androidx.fragment.app.Fragment
import com.example.app_domain.models.central.News
import com.example.app_ui.common.AppScreen

class DetailNewsScreen(
    val news: News
): AppScreen() {
    override fun getFragment(): Fragment = DetailNewsFragment.newInstance(this)
}