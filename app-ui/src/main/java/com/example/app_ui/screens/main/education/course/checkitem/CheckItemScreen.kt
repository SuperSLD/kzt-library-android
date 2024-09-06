package com.example.app_ui.screens.main.education.course.checkitem

import com.example.app_domain.models.courses.CheckListItem
import com.example.app_domain.models.courses.Course
import com.example.app_ui.common.AppScreen

class CheckItemScreen(
    val checkListItem: CheckListItem,
): AppScreen() {

    override fun getFragment() = CheckItemFragment.newInstance(this)
}