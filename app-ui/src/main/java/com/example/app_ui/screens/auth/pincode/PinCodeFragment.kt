package com.example.app_ui.screens.auth.pincode

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.ext.shortVibration
import kotlinx.android.synthetic.main.fragment_pincode.*
import com.example.app_ui.common.core.base.BaseFragment

class PinCodeFragment : BaseFragment(R.layout.fragment_pincode), PinCodeView {

    companion object {
        fun newInstance(screen: PinCodeScreen) = PinCodeFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: PinCodePresenter

    @ProvidePresenter
    fun providePresenter() = PinCodePresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as PinCodeScreen,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        with(pin_code_dots) {
            init(
                size = 4,
                colorError = R.color.colorAlertRed,
                colorDisable = R.color.colorTextSecondary,
                colorEnable = R.color.colorPrimary,
                borderDisable = R.color.colorTextSecondary,
            )
            onSuccess {
                presenter.onPinSuccessInput(it)
            }
            onError {
                vibrator.shortVibration()
            }
            onUpdate {
                pin_code_buttons.updateState(it)
            }
        }

        with(pin_code_buttons) {
            onDelete{ pin_code_dots.deleteLast() }
            onAdd{ pin_code_dots.addSymbol(it) }
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun setTitleRes(res: Int) {
        pin_code_title.setText(res)
    }

    override fun setCorrectPin(pin: String) {
        pin_code_dots.setCorrectPin(pin)
    }
}