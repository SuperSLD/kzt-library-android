package com.example.app_ui.screens.main.documents.document

import com.example.app_domain.models.books.Book
import com.example.app_ui.common.AppScreen

class DocumentScreen(
    val book: Book,
): AppScreen() {

    override fun getFragment() = DocumentFragment.newInstance(this)
}