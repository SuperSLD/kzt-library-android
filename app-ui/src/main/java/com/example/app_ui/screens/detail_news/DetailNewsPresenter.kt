package com.example.app_ui.screens.detail_news

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.usecases.central.GetCentralUseCase
import com.example.app_domain.usecases.join_event.JoinEventUseCase
import com.example.app_ui.R
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.auth.pincode.PinCodeScreen
import com.example.app_ui.screens.auth.pincode.PinCodeScreenMode
import com.example.app_ui.screens.detail_news.success_subscribe.SuccessSubscribeScreen
import com.example.app_ui.screens.registration.RegistrationScreen
import com.example.app_ui.screens.success.SuccessNextAction
import com.example.app_ui.screens.success.SuccessScreen
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class DetailNewsPresenter(
    private val params: DetailNewsScreen
): BasePresenter<DetailNewsView>() {

    private val getJoinEventUseCase: JoinEventUseCase by inject()
    private val bottomVisibilityController: BottomVisibilityController by inject()

    init {
        viewState.showEventInfo(news = params.news)
    }

    override fun attachView(view: DetailNewsView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    fun subscribeOnEvent() {
        launchUI(createHandler {
            viewState.showErrorToast(it.message!!)
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            withIO { getJoinEventUseCase(params.news.id) }
            router?.replaceScreen(
                SuccessSubscribeScreen(params.news)
            )
        }
    }

    fun onBack() = router?.exit()
}