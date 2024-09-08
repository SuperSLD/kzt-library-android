package com.example.app_ui.screens.main.documents.document

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.books.Book
import com.example.app_domain.usecases.book.OrderBookUseCase
import com.example.app_domain.usecases.book.RenewBookUseCase
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.toCalendar
import com.example.app_ui.common.core.base.toDateString
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.ext.createHandler
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject
import java.util.Calendar

@InjectViewState
class DocumentPresenter(
    private val params: DocumentScreen,
) : BasePresenter<DocumentView>() {

    var book = params.book

    private val orderBookUseCase: OrderBookUseCase by inject()
    private val renewBookUseCase: RenewBookUseCase by inject()

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: DocumentView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showBook(params.book)
    }

    fun orderBook() {
        launchUI(createHandler {
            viewState.showErrorToast(it.message!!)
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            withIO { orderBookUseCase(params.book.id) }
            viewState.orderBook()
            viewState.toggleLoading(false)
        }
    }

    fun renewBook() {
        launchUI(createHandler {
            viewState.showErrorToast(it.message!!)
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            withIO { renewBookUseCase(params.book.id) }
            viewState.renewBook(getIncreasedBookDate())
            viewState.toggleLoading(false)
        }
    }

    private fun getIncreasedBookDate(): Book {
        val date = book.date!!.toCalendar()
        date.add(Calendar.DATE, 10)
        val newDate = date.toDateString()
        book = book.copy(date = newDate)
        return book
    }

    fun onBack() = router?.exit()
}