package com.example.app_ui.screens.main.profile.achivments

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class AchivmentsPresenter(
    private val params: AchivmentsScreen,
) : BasePresenter<AchivmentsView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: AchivmentsView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showAchivments(params.achivments)
    }

    fun onBack() = router?.exit()
}