package com.example.app_ui.screens.registration

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.usecases.user.RegistrationUseCase
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.auth.login.LoginScreen
import com.example.app_ui.screens.auth.pincode.PinCodeScreen
import com.example.app_ui.screens.auth.pincode.PinCodeScreenMode
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class RegistrationPresenter(
    private val params: RegistrationScreen,
):BasePresenter<RegistrationView>() {

    private val registrationUseCase: RegistrationUseCase by inject()

    private var name: String? = null
    private var lastname: String? = null
    private var midname: String? = null

    fun onNameChange(name: String) {
        this.name = name
        checkButtonState()
    }

    fun onLastnameChange(lastname: String) {
        this.lastname = lastname
        checkButtonState()
    }

    fun onMidNameChange(midName: String) {
        this.midname = midName
        checkButtonState()
    }

    private fun checkButtonState() {
        viewState.toggleButtonEnabled(
            !name.isNullOrEmpty() &&
                    !lastname.isNullOrEmpty() &&
                    !midname.isNullOrEmpty()
        )
    }

    fun onRegistration(
        name: String,
        lastName: String,
        midName: String,
        avatar: String?
    ) {
        launchUI(createHandler {
            viewState.showErrorToast(it.message!!)
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            withIO { registrationUseCase(params.login, name, lastName, midName, avatar) }
            router?.navigateTo(PinCodeScreen(mode = PinCodeScreenMode.CREATE))
        }
    }

    fun onBack() = router?.navigateTo(LoginScreen())
}