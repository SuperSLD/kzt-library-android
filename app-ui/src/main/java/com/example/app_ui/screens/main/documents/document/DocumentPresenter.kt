package com.example.app_ui.screens.main.documents.document

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class DocumentPresenter(
    private val params: DocumentScreen,
) : BasePresenter<DocumentView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: DocumentView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showDocument(params.document)
    }

    fun onBack() = router?.exit()
}