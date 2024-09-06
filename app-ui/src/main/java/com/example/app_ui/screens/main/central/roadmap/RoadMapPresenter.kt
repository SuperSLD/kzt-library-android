package com.example.app_ui.screens.main.central.roadmap

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.controllers.NavigationController
import com.example.app_ui.screens.main.central.roadmap.navigation.NavigationScreen
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class RoadMapPresenter : BasePresenter<RoadMapView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val navigationController: NavigationController by inject()

    override fun attachView(view: RoadMapView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
        navigationController.get()?.let { viewState.findRoad(it) }
    }

    fun clearNavData() {
        navigationController.clearData()
    }

    fun onOpenNavPoints() {
        router?.navigateTo(NavigationScreen())
    }

    fun back() {
        navigationController.clearData()
        router?.exit()
    }
}