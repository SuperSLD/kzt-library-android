package com.example.app_ui.screens.detail_news.success_subscribe

import androidx.fragment.app.Fragment
import com.example.app_domain.models.central.News
import com.example.app_ui.common.AppScreen

class SuccessSubscribeScreen(
    val news: News
): AppScreen() {
    override fun getFragment(): Fragment = SuccessSubscribeFragment.newInstance(this)
}