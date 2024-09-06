package com.example.app_domain.controllers

import online.jutter.roadmapview.data.models.map.RMRoom
import com.example.app_domain.controllers.datacontrol.PublishDataController


class SelectRoomController {
    val state = PublishDataController<RMRoom>()

    fun select(room: RMRoom) = state.emit(room)
}