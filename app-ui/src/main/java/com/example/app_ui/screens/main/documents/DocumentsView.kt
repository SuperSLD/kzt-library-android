package com.example.app_ui.screens.main.documents

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.documents.Document
import com.example.app_domain.models.documents.Request

interface DocumentsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDocument(items: List<Document>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showRequests(items: List<Request>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)
}
