package com.example.app_ui.screens.auth.pincode

import com.example.app_ui.common.AppScreen

class PinCodeScreen(
    val mode: PinCodeScreenMode,
    val pin: String? = null,
) : AppScreen() {

    override fun getFragment() = PinCodeFragment.newInstance(this)
}