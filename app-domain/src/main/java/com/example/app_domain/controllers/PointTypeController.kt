package com.example.app_domain.controllers

import com.example.app_domain.controllers.datacontrol.PublishDataController

class PointTypeController {
    val state = PublishDataController<PointType>()

    fun select(pointType: PointType) = state.emit(pointType)
}

enum class PointType {
    ROOM,
    MAP,
    QR,
}