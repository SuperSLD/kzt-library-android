package com.example.app_ui.screens.main.sections.detail

import com.example.app_domain.models.sections.Section
import com.example.app_ui.common.AppScreen

class SectionDetailScreen(
    val section: Section,
): AppScreen() {

    override fun getFragment() = SectionDetailFragment.newInstance(this)
}