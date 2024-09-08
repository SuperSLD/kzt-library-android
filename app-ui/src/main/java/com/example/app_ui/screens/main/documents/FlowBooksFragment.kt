package com.example.app_ui.screens.main.documents

import android.os.Bundle
import android.view.View
import com.example.app_ui.common.AppScreen
import com.example.app_ui.common.core.base.FlowFragment
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

class FlowDocumentsFragment : FlowFragment(ROUTER) {

    companion object {
        const val ROUTER = "DOCUMENTS_ROUTER"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.applyCommands(
                arrayOf(
                    BackTo(null),
                    Replace(BooksScreen())
                )
            )
        }
    }
}

class FlowDocumentsScreen: AppScreen() {

    override fun getFragment() = FlowDocumentsFragment()
}