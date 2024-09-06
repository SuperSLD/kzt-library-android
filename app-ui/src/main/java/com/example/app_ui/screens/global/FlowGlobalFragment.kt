package com.example.app_ui.screens.global

import android.os.Bundle
import android.view.View
import com.example.app_ui.screens.splash.SplashScreen
import com.example.app_ui.common.core.base.FlowFragment
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

class FlowGlobalFragment : FlowFragment(ROUTER) {

    companion object {
        const val ROUTER = "APP_ROUTER"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.applyCommands(
                arrayOf(
                    BackTo(null),
                    Replace(SplashScreen())
                )
            )
        }
    }
}