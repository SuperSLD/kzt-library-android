package com.example.app_ui.screens.main.documents.request

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.usecases.documents.SendRequestUseCase
import com.example.app_ui.R
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.success.SuccessNextAction
import com.example.app_ui.screens.success.SuccessScreen
import online.juter.supersld.view.input.form.JTForm
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import org.koin.core.inject

@InjectViewState
class RequestPresenter(
    private val params: RequestScreen,
) : BasePresenter<RequestView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val sendRequestUseCase: SendRequestUseCase by inject()

    override fun attachView(view: RequestView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val mapper = RequestMapperUi()
        viewState.showForm(form = mapper.map(params.request))
    }

    fun onFinish(form: JTForm) {
        launchUI(createHandler {
            it.printStackTrace()
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            withIO { sendRequestUseCase(form.name!!) }
            router?.replaceScreen(
                SuccessScreen(
                    iconRes = R.drawable.ic_reauest_finish,
                    titleRes = R.string.documents_request_finish_title,
                    subtitleRes = R.string.documents_request_finish_subtitle,
                    buttonRes = R.string.documents_request_finish_button,
                    nextAction = SuccessNextAction.BACK,
                )
            )
        }
    }

    fun onBack() = router?.exit()
}