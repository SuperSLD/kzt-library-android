package com.example.app_ui.screens.main.education.course

import com.example.app_domain.models.courses.Course
import com.example.app_ui.common.AppScreen

class CourseScreen(
    val course: Course,
): AppScreen() {

    override fun getFragment() = CourseFragment.newInstance(this)
}