package com.example.app_ui.screens.main.education.course

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.courses.Course
import com.example.app_domain.models.documents.Document
import com.example.app_domain.models.documents.Request
import online.juter.supersld.view.input.form.JTForm

interface CourseView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCourse(course: Course)
}
