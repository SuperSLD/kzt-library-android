package com.example.app_ui.screens.main.sections

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.sections.Section

interface SectionsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSections(list: List<Section>)
}
