package com.example.app_ui.screens.main.education.course

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.courses.CheckListItem
import com.example.app_domain.models.courses.Lesson
import com.example.app_ui.screens.main.education.course.checkitem.CheckItemScreen
import com.example.app_ui.screens.main.education.course.lesson.LessonScreen
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class CoursePresenter(
    private val params: CourseScreen,
) : BasePresenter<CourseView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: CourseView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showCourse(params.course)
    }

    fun onOpenCheckListItem(checkListItem: CheckListItem) {
        router?.navigateTo(CheckItemScreen(checkListItem))
    }

    fun onOpenLesson(lesson: Lesson) {
        router?.navigateTo(LessonScreen(lesson))
    }

    fun onBack() = router?.exit()
}