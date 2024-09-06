package com.example.app_ui.screens.auth.pincode

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface PinCodeView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTitleRes(res: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCorrectPin(pin: String)
}
