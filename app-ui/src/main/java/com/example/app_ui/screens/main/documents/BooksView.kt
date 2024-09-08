package com.example.app_ui.screens.main.documents

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.books.Book

interface BooksView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showRecommendation(items: List<Book>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNewBooks(items: List<Book>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMyBooks(items: List<Book>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)
}
