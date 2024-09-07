package com.example.app_ui.screens.detail_news.success_subscribe

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.central.News
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class SuccessSubscribePresenter(
    private val params: SuccessSubscribeScreen
): BasePresenter<SuccessSubscribeView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: SuccessSubscribeView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    fun getNews(): News = params.news

    fun onClickToAddToCalendar() {
        viewState.addEventToCalendar(params.news)
    }

    fun onBack() = router?.exit()
}