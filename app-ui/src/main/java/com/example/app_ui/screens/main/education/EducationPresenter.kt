package com.example.app_ui.screens.main.education

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.courses.Course
import com.example.app_domain.usecases.courses.GetCoursesUseCase
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.main.education.course.CourseScreen
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import org.koin.core.inject

@InjectViewState
class EducationPresenter : BasePresenter<EducationView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val getCoursesUseCase: GetCoursesUseCase by inject()

    private var courses: List<Course>? = null
    private var selectedTab = 0

    override fun attachView(view: EducationView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(true)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadCourses()
    }

    private fun loadCourses() {
        launchUI(createHandler { it.printStackTrace() }) {
            viewState.toggleLoading(true)
            courses = withIO { getCoursesUseCase() }
            viewState.showNewCourses(courses!!.filter { it.new })
            showSortedCourses()
            viewState.toggleLoading(false)
        }
    }

    fun onChangeTab(tab: Int) {
        selectedTab = tab
        showSortedCourses()
    }

    fun onOpenCourse(course: Course) {
        router?.navigateTo(CourseScreen(course))
    }

    private fun showSortedCourses() {
        val sortedCourses = when(selectedTab) {
            0 ->  courses!!.filter { it.important && it.percent() != 100 }
            1 ->  courses!!.filter { !it.important && it.percent() != 100 }
            2 ->  courses!!.filter { it.percent() == 100 }
            else -> courses!!
        }
        viewState.showFilteredCourses(sortedCourses)
    }

    fun onBack() = router?.exit()
}