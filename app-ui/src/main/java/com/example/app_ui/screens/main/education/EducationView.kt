package com.example.app_ui.screens.main.education

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.courses.Course

interface EducationView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNewCourses(items: List<Course>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFilteredCourses(items: List<Course>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)
}
