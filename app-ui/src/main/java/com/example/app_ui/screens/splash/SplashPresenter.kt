package com.example.app_ui.screens.splash

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.app_domain.usecases.user.IsAuthUseCase
import com.example.app_domain.usecases.user.PinCodeIsSetUseCase
import com.example.app_ui.ext.createEmptyHandler
import com.example.app_ui.screens.auth.login.LoginScreen
import com.example.app_ui.screens.auth.pincode.PinCodeScreen
import com.example.app_ui.screens.auth.pincode.PinCodeScreenMode
import kotlinx.coroutines.delay
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchIO
import com.example.app_ui.common.core.base.withUI
import org.koin.core.inject

@InjectViewState
class SplashPresenter : BasePresenter<MvpView>() {

    private val isAuthUseCase: IsAuthUseCase by inject()
    private val pinCodeIsSetUseCase: PinCodeIsSetUseCase by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        start()
    }

    private fun start() {
        launchIO(createEmptyHandler()) {
            delay(2000)
            withUI {
                if (isAuthUseCase()) {
                    if (pinCodeIsSetUseCase()) {
                        router?.newRootScreen(PinCodeScreen(mode = PinCodeScreenMode.INPUT))
                    } else {
                        router?.newRootScreen(PinCodeScreen(mode = PinCodeScreenMode.CREATE))
                    }
                } else {
                    router?.newRootScreen(LoginScreen())
                }
            }
        }
    }

    fun onBack() = router?.exit()
}