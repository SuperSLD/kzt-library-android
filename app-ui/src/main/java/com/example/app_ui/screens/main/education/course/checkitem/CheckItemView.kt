package com.example.app_ui.screens.main.education.course.checkitem

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.courses.CheckListItem

interface CheckItemView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCheckItem(checkListItem: CheckListItem)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)
}
