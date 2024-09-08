package com.example.app_ui.screens.main.central.roadmap.navigation.pointtype

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.app_domain.controllers.PointType
import com.example.app_domain.controllers.PointTypeController
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class PointTypeBSPresenter : BasePresenter<MvpView>() {
    private val pointTypeController: PointTypeController by inject()

    fun mapPoint() = pointTypeController.select(PointType.MAP)
    fun roomPoint() = pointTypeController.select(PointType.ROOM)
    fun qrPoint() = pointTypeController.select(PointType.QR)
}