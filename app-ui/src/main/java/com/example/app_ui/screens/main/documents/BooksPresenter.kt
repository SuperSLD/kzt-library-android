package com.example.app_ui.screens.main.documents

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.books.Book
import com.example.app_domain.usecases.book.GetBooksContentUseCase
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.main.documents.document.DocumentScreen
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class BooksPresenter : BasePresenter<BooksView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val getBooksContentUseCase: GetBooksContentUseCase by inject()

    private var selectedTab = 0
    private var myBooks: List<Book>? = null
    private var newBooks: List<Book>? = null
    private var recommendBooks: List<Book>? = null

    override fun attachView(view: BooksView?) {
        super.attachView(view)
        loadContent()
        bottomVisibilityController.setVisible(true)
    }

    private fun loadContent() {
        launchUI(createHandler { it.printStackTrace() }) {
            viewState.toggleLoading(true)
            val content = withIO { getBooksContentUseCase() }
            Log.d("CONTENT", "loadContent: $content")
            myBooks = content.myBook
            newBooks = content.new
            recommendBooks = content.rec
            viewState.showRecommendation(content.rec)
            viewState.showMyBooks(content.myBook)
            viewState.toggleLoading(false)
        }
    }

    fun onChangeTab(tab: Int) {
        selectedTab = tab
        when(selectedTab) {
            0 -> recommendBooks?.let { viewState.showRecommendation(it) }
            1 -> newBooks?.let { viewState.showNewBooks(it) }
            else -> {}
        }
    }

    fun onOpenBook(book: Book) {
        router?.navigateTo(DocumentScreen(book))
    }

    fun onBack() = router?.exit()
}