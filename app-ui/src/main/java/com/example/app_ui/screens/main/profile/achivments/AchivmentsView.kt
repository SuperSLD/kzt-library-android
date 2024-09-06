package com.example.app_ui.screens.main.profile.achivments

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.user.Achivment

interface AchivmentsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showAchivments(items: List<Achivment>)
}