package com.example.app_ui.screens.main.documents.document

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.documents.Document

interface DocumentView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDocument(document: Document)
}
