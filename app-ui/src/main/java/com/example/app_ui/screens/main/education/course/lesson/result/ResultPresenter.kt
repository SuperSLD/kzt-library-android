package com.example.app_ui.screens.main.education.course.lesson.result

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class ResultPresenter(
    private val params: ResultScreen,
) : BasePresenter<ResultView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun attachView(view: ResultView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showResult(params.result)
    }

    fun onBack() = router?.exit()
}