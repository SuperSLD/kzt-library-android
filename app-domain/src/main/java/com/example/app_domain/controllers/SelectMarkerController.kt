package com.example.app_domain.controllers

import online.jutter.roadmapview.data.models.map.RMMarker
import com.example.app_domain.controllers.datacontrol.PublishDataController


class SelectMarkerController {
    val state = PublishDataController<RMMarker>()

    fun select(notifications: RMMarker) = state.emit(notifications)
}