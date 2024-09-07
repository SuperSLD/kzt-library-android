package com.example.app_ui.screens.registration

import androidx.fragment.app.Fragment
import com.example.app_ui.common.AppScreen

class RegistrationScreen(
    val login: String
): AppScreen() {
    override fun getFragment(): Fragment = RegistrationFragment.newInstance(this)
}