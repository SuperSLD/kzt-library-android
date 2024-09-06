package com.example.app_ui.screens.main.central

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.central.Central
import com.example.app_domain.models.central.News

interface CentralView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCentral(central: Central)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)
}
