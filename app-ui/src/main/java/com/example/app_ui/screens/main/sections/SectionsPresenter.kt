package com.example.app_ui.screens.main.sections

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.sections.Section
import com.example.app_domain.usecases.sections.GetSectionsUseCase
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import com.example.app_ui.ext.createEmptyHandler
import com.example.app_ui.screens.main.sections.detail.SectionDetailScreen
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class SectionsPresenter : BasePresenter<SectionsView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val getSectionsUseCase: GetSectionsUseCase by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadSections()
    }

    override fun attachView(view: SectionsView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(true)
    }

    private fun loadSections() {
        launchUI(createEmptyHandler()) {
            viewState.toggleLoading(true)
            val sections = withIO { getSectionsUseCase() }
            viewState.showSections(sections)
            viewState.toggleLoading(false)
        }
    }

    fun onOpenSection(section: Section) {
        router?.navigateTo(SectionDetailScreen(section))
    }

    fun onBack() = router?.exit()
}