package com.example.app_ui.screens.main.education.course.lesson

import com.example.app_domain.models.courses.Lesson
import com.example.app_domain.models.documents.Request
import com.example.app_ui.common.AppScreen

class LessonScreen(
    val lesson: Lesson,
): AppScreen() {

    override fun getFragment() = LessonFragment.newInstance(this)
}