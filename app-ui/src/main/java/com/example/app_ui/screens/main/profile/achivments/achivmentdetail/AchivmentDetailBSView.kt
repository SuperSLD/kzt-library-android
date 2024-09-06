package com.example.app_ui.screens.main.profile.achivments.achivmentdetail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.user.Achivment

interface AchivmentDetailBSView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showAchivment(achivment: Achivment)
}