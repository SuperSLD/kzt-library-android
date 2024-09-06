package com.example.app_domain.controllers

import com.example.app_domain.controllers.datacontrol.PublishDataController

class BottomVisibilityController {
    val state = PublishDataController<Boolean>()
    fun setVisible(visible: Boolean) = state.emit(visible)
}