package com.example.app_ui.screens.main.central.roadmap.selectroom

import com.arellomobile.mvp.MvpView
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.controllers.SelectRoomController
import online.jutter.roadmapview.data.models.map.RMRoom
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

class SelectRoomPresenter : BasePresenter<MvpView>() {

    private val selectRoomController: SelectRoomController by inject()
    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: MvpView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    fun onRoomClick(room: RMRoom) {
        selectRoomController.select(room)
        back()
    }

    fun back() {
        router?.exit()
    }
}