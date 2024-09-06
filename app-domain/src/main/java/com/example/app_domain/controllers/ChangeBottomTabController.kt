package com.example.app_domain.controllers

import com.example.app_domain.controllers.datacontrol.PublishDataController

class ChangeBottomTabController {

    val state = PublishDataController<Int>()
    fun select(tab: Int) = state.emit(tab)
}