package com.example.app_ui.screens.main.documents.document

import com.example.app_domain.models.documents.Document
import com.example.app_domain.models.documents.Request
import com.example.app_ui.common.AppScreen

class DocumentScreen(
    val document: Document,
): AppScreen() {

    override fun getFragment() = DocumentFragment.newInstance(this)
}