package com.example.app_ui.screens.detail_news

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.central.News

interface DetailNewsView: MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun showEventInfo(news: News)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast(text: String)
}