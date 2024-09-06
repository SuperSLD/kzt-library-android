package com.example.app_ui.screens.main.central.roadmap.selectpoint

import com.example.app_ui.common.AppScreen

class SelectPointScreen(
    val id: String,
    val titleRes: Int,
    val descriptionRes: Int,
    val symbol: String,
    val color: String,
): AppScreen() {

    override fun getFragment() = SelectPointFragment.createInstance(this)
}