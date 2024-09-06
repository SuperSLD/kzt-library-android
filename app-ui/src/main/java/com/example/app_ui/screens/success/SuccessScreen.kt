package com.example.app_ui.screens.success

import com.example.app_ui.common.AppScreen

class SuccessScreen(
    val titleRes: Int,
    val subtitleRes: Int,
    val iconRes: Int,
    val buttonRes: Int,
    val nextAction: SuccessNextAction,
    val nextScreen: AppScreen? = null,
) : AppScreen() {

    override fun getFragment() = SuccessFragment.newInstance(this)
}

enum class SuccessNextAction{
    NEXT,
    ROOT,
    BACK,
}