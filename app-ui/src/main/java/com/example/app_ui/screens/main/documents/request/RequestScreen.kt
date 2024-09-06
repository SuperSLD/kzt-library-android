package com.example.app_ui.screens.main.documents.request

import com.example.app_domain.models.documents.Request
import com.example.app_ui.common.AppScreen

class RequestScreen(
    val request: Request,
): AppScreen() {

    override fun getFragment() = RequestFragment.newInstance(this)
}