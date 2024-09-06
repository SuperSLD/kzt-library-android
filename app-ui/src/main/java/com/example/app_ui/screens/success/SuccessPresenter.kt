package com.example.app_ui.screens.success

import com.arellomobile.mvp.InjectViewState
import online.jutter.supersld.common.base.BasePresenter

@InjectViewState
class SuccessPresenter(
    private val params: SuccessScreen,
) : BasePresenter<SuccessView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setContent(
            title = params.titleRes,
            subtitle = params.subtitleRes,
            buttonTitle = params.buttonRes,
            icon = params.iconRes,
        )
    }

    fun onConfirm() {
        when(params.nextAction) {
            SuccessNextAction.NEXT -> {
                router?.navigateTo(params.nextScreen!!)
            }
            SuccessNextAction.BACK -> {
                router?.exit()
            }
            SuccessNextAction.ROOT -> {
                router?.newRootScreen(params.nextScreen!!)
            }
        }
    }

    fun onBack() = router?.exit()
}