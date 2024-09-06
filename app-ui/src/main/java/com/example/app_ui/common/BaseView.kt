package com.example.app_ui.common

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface BaseView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)
}