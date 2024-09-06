package com.example.app_ui.screens.main.education.course.checkitem

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.usecases.courses.CheckUseCase
import com.example.app_ui.ext.createHandler
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import org.koin.core.inject

@InjectViewState
class CheckItemPresenter(
    private val params: CheckItemScreen,
) : BasePresenter<CheckItemView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val checkUseCase: CheckUseCase by inject()

    override fun attachView(view: CheckItemView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showCheckItem(params.checkListItem)
    }

    fun onCheck() {
        launchUI(createHandler {
            it.printStackTrace()
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            withIO { checkUseCase(params.checkListItem.id) }
            params.checkListItem.checked = true
            onBack()
        }
    }

    fun onBack() = router?.exit()
}