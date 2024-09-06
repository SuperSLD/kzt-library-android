package com.example.app_ui.screens.main.profile

import android.os.Bundle
import android.view.View
import com.example.app_ui.common.AppScreen
import com.example.app_ui.common.core.base.FlowFragment
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

class FlowProfileFragment : FlowFragment(ROUTER) {

    companion object {
        const val ROUTER = "PROFILE_ROUTER"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.applyCommands(
                arrayOf(
                    BackTo(null),
                    Replace(ProfileScreen())
                )
            )
        }
    }
}

class FlowProfileScreen: AppScreen() {

    override fun getFragment() = FlowProfileFragment()
}