package com.example.app_ui.screens.main.sections.detail

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.sections.Section
import com.example.app_domain.usecases.sections.GetSectionsUseCase
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.ext.createEmptyHandler
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class SectionDetailPresenter(
    private val params: SectionDetailScreen,
) : BasePresenter<SectionDetailView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        bottomVisibilityController.setVisible(false)
        viewState.showSection(params.section)
    }

    fun onBack() = router?.exit()
}