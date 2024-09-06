package com.example.app_ui.screens.main.central.roadmap.selectpoint

import com.arellomobile.mvp.MvpView
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.controllers.SelectMarkerController
import online.jutter.roadmapview.data.models.map.RMMarker
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

class SelectPointPresenter : BasePresenter<MvpView>() {

    private val selectMarkerController: SelectMarkerController by inject()
    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: MvpView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    fun onDoneClick(marker: RMMarker) {
        selectMarkerController.select(marker)
        back()
    }

    fun back() {
        router?.exit()
    }
}