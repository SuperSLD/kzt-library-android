package com.example.app_ui.screens.auth.pincode

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.usecases.user.GetPinCodeUseCase
import com.example.app_domain.usecases.user.SetPinCodeUseCase
import com.example.app_ui.R
import com.example.app_ui.screens.main.FlowMainScreen
import com.example.app_ui.screens.success.SuccessNextAction
import com.example.app_ui.screens.success.SuccessScreen
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class PinCodePresenter(
    private val params: PinCodeScreen,
) : BasePresenter<PinCodeView>() {

    private val getPinCodeUseCase: GetPinCodeUseCase by inject()
    private val setPinCodeUseCase: SetPinCodeUseCase by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        when(params.mode) {
            PinCodeScreenMode.CREATE -> {
                viewState.setTitleRes(R.string.pin_code_create_title)
            }
            PinCodeScreenMode.CONFIRM -> {
                viewState.setTitleRes(R.string.pin_code_repeat_title)
                viewState.setCorrectPin(params.pin!!)
            }
            PinCodeScreenMode.INPUT -> {
                viewState.setTitleRes(R.string.pin_code_title)
                viewState.setCorrectPin(getPinCodeUseCase())
            }
        }
    }

    fun onPinSuccessInput(pin: String) {
        when(params.mode) {
            PinCodeScreenMode.CREATE -> {
                router?.navigateTo(PinCodeScreen(PinCodeScreenMode.CONFIRM, pin))
            }
            PinCodeScreenMode.CONFIRM -> {
                setPinCodeUseCase(pin)
                router?.navigateTo(SuccessScreen(
                    iconRes = R.drawable.ic_looking_out,
                    titleRes = R.string.pin_success_title,
                    subtitleRes = R.string.pin_success_subtitle,
                    buttonRes = R.string.pin_success_button,
                    nextScreen = FlowMainScreen(),
                    nextAction = SuccessNextAction.ROOT,
                ))
            }
            PinCodeScreenMode.INPUT -> {
                router?.newRootScreen(FlowMainScreen())
            }
        }
    }

    fun onBack() = router?.exit()
}

enum class PinCodeScreenMode{
    CREATE,
    CONFIRM,
    INPUT,
}