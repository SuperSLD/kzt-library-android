package com.example.app_ui.screens.auth.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(isShow: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast(text: String)
}
