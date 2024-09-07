package com.example.app_ui.screens.main.central

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.central.News
import com.example.app_domain.usecases.central.GetCentralUseCase
import com.example.app_ui.ext.createEmptyHandler
import com.example.app_ui.screens.main.central.roadmap.RoadMapScreen
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.screens.detail_news.DetailNewsScreen
import org.koin.core.inject

@InjectViewState
class CentralPresenter : BasePresenter<CentralView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val getCentralUseCase: GetCentralUseCase by inject()

    override fun attachView(view: CentralView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(true)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCentral()
    }

    fun onNewsClick(news: News) {
        router?.navigateTo(DetailNewsScreen(news))
    }

    private fun loadCentral() {
        launchUI(createEmptyHandler()) {
            viewState.toggleLoading(true)
            val central = withIO { getCentralUseCase() }
            viewState.showCentral(central)
            viewState.toggleLoading(false)
        }
    }

    fun onOpenMap() {
        router?.navigateTo(RoadMapScreen())
    }

    fun onBack() = router?.exit()
}