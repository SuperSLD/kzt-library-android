package com.example.app_ui

import androidx.fragment.app.Fragment
import com.example.app_ui.common.core.base.ActivityBase
import com.example.app_ui.screens.global.FlowGlobalScreen
import online.jutter.roadmapview.RMMapView

class MainActivity : ActivityBase() {

    override fun getStartFragment() = FlowGlobalScreen().fragment

    override fun getStatusAndNavigationColor(): Pair<Int, Int> {
        RMMapView.setApiKey("4c2bd9ec-f02f-4c2e-be8d-5f5c79addd36")
        return Pair(R.color.colorStatusBar, R.color.colorNavigationBar)
    }

    override fun themeIsDay() = true
}

fun Fragment.getMainActivity() = requireActivity() as MainActivity