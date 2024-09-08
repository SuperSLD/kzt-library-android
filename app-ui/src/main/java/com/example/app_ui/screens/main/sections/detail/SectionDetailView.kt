package com.example.app_ui.screens.main.sections.detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.sections.Section

interface SectionDetailView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSection(section: Section)
}
