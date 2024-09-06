package com.example.app_ui.screens.main

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class MainContainerPresenter : BasePresenter<MainContainerView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: MainContainerView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(true)
        viewState.initBottomNavigation()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        listenBottomNavigationVisibility()
    }


    private fun listenBottomNavigationVisibility() {
        bottomVisibilityController.state
            .listen {
                viewState.changeBottomNavigationVisibility(it)
            }.connect()
    }
}