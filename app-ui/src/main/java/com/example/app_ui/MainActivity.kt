package com.example.app_ui

import androidx.fragment.app.Fragment
import com.example.app_ui.screens.global.FlowGlobalScreen
import online.jutter.roadmapview.RMMapView
import com.example.app_ui.common.core.base.ActivityBase

class MainActivity : ActivityBase() {

    override fun getStartFragment() = FlowGlobalScreen().fragment

    override fun getStatusAndNavigationColor(): Pair<Int, Int> {
        RMMapView.setApiKey("755ceb95-82d1-45e7-9e5e-8bd926364cf8")
        return Pair(R.color.colorStatusBar, R.color.colorNavigationBar)
    }

    override fun themeIsDay() = true
}

fun Fragment.getMainActivity() = requireActivity() as MainActivity