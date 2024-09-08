package com.example.app_ui.screens.main.documents.document

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.books.Book

interface DocumentView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showBook(book: Book)

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun orderBook()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renewBook(book: Book)
}
