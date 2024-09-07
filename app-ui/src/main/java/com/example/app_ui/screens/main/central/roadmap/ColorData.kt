package com.example.app_ui.screens.main.central.roadmap

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.Fragment
import online.jutter.roadmapview.RMColorData
import online.jutter.roadmapview.extensions.createColor

fun Context.getMapColorData(
    back: Int = createColor(255, 255, 255),
    backD: Int = Color.parseColor("#121212"),
): RMColorData {
    val isDay = true
    return if (isDay) {
        RMColorData(
            buildingColor = Color.parseColor("#F1F1F1"),
            buildingWithFloorsColor = Color.parseColor("#5B97A0"),
            backColor = back,
            navBorder = createColor(0, 0, 0),
            roadColors = listOf(
                Color.parseColor("#DCDCDC"), // серый
                Color.parseColor("#DCDCDC"), // серый
                Color.parseColor("#DCDCDC"), // серый
                Color.parseColor("#DCDCDC"), // серый
            ),
            floorBackColor = createColor(103, 103, 103),
        )
    } else {
        RMColorData(
            buildingColor = Color.parseColor("#303030"),
            buildingWithFloorsColor = Color.parseColor("#0D83E4"),
            backColor = backD,
            navBorder = createColor(255, 255, 255),
            roadColors = listOf(
                Color.parseColor("#505050"), // серый
                Color.parseColor("#505050"), // серый
                Color.parseColor("#505050"), // серый
                Color.parseColor("#505050"), // серый
            ),
            floorBackColor = createColor(10, 10, 10),
        )
    }
}

fun Fragment.getMapColorData(
    back: Int = createColor(255, 255, 255),
    backD: Int = createColor(12, 12, 12),
) = requireContext().getMapColorData(back, backD)