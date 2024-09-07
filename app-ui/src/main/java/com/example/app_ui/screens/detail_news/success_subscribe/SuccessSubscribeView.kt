package com.example.app_ui.screens.detail_news.success_subscribe

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.central.News

interface SuccessSubscribeView: MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun addEventToCalendar(news: News)
}