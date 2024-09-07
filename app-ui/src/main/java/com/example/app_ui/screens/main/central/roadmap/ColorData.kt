package com.example.app_ui.screens.main.central.roadmap

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.Fragment
import online.jutter.roadmapview.RMColorData
import online.jutter.roadmapview.extensions.createColor
import online.jutter.roadmapview.extensions.createColorInt

fun Context.getMapColorData(
    back: FloatArray = createColor(255, 255, 255),
): RMColorData {
        return RMColorData(
            buildingColor = createColor(241, 241, 241),
            buildingWithFloorsColor = createColor(91, 151, 160),
            backColor = back,
            navBorder = createColorInt(0, 0, 0),
            roadColors = listOf(
                createColor(220, 220, 220), // серый
                createColor(220, 220, 220), // серый
                createColor(220, 220, 220), // серый
                createColor(220, 220, 220), // серый
            ),
            floorBackColor = createColor(103, 103, 103),
        )
}

fun Fragment.getMapColorData(
    back: FloatArray = createColor(255, 255, 255),
) = requireContext().getMapColorData(back)