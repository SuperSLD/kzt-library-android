package com.example.app_ui.screens.main

import com.example.app_ui.common.AppScreen

class MainContainerScreen : AppScreen() {

    override fun getFragment() = MainContainerFragment.create()
}