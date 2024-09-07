package com.example.app_ui.screens.auth.login

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.usecases.user.LoginUseCase
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.auth.pincode.PinCodeScreen
import com.example.app_ui.screens.auth.pincode.PinCodeScreenMode
import com.example.app_ui.screens.registration.RegistrationScreen
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class LoginPresenter : BasePresenter<LoginView>() {

    private val loginUseCase: LoginUseCase by inject()

    fun onLogin(login: String) {
        launchUI(createHandler {
            viewState.showErrorToast(it.message!!)
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            val authResponse = withIO { loginUseCase(login) }
            if (authResponse.isRegistered) {
                router?.navigateTo(PinCodeScreen(mode = PinCodeScreenMode.CREATE))
            } else {
                router?.navigateTo(RegistrationScreen(login))
            }
        }
    }

    fun onBack() = router?.exit()
}