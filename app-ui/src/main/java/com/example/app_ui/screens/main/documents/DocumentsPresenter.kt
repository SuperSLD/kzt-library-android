package com.example.app_ui.screens.main.documents

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.documents.Document
import com.example.app_domain.models.documents.Request
import com.example.app_domain.usecases.documents.GetDocumentsContentUseCase
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.main.documents.document.DocumentScreen
import com.example.app_ui.screens.main.documents.request.RequestScreen
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import org.koin.core.inject

@InjectViewState
class DocumentsPresenter : BasePresenter<DocumentsView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val getDocumentsContentUseCase: GetDocumentsContentUseCase by inject()

    override fun attachView(view: DocumentsView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(true)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadContent()
    }

    private fun loadContent() {
        launchUI(createHandler { it.printStackTrace() }) {
            viewState.toggleLoading(true)
            val content = withIO { getDocumentsContentUseCase() }
            viewState.showRequests(content.requests)
            viewState.showDocument(content.documents)
            viewState.toggleLoading(false)
        }
    }

    fun onOpenDocument(document: Document) {
        router?.navigateTo(DocumentScreen(document))
    }

    fun onOpenRequest(request: Request) {
        router?.navigateTo(RequestScreen(request))
    }

    fun onBack() = router?.exit()
}