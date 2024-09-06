package com.example.app_ui.screens.main.education.course.lesson.result

import com.example.app_ui.common.AppScreen

class ResultScreen(
    val result: Pair<Int, Int>,
): AppScreen() {

    override fun getFragment() = ResultFragment.newInstance(this)
}