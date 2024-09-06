package com.example.app_ui.screens.main.challenges

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.app_domain.controllers.BottomVisibilityController
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class ChallengesPresenter : BasePresenter<MvpView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: MvpView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(true)
    }

    fun onBack() = router?.exit()
}