package com.example.app_ui.screens.main.profile

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.models.Idea
import com.example.app_domain.models.user.Achivment
import com.example.app_domain.usecases.user.GetProfileUseCase
import com.example.app_ui.ext.createEmptyHandler
import com.example.app_ui.screens.main.profile.achivments.AchivmentsScreen
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import org.koin.core.inject

@InjectViewState
class ProfilePresenter : BasePresenter<ProfileView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val getProfileUseCase: GetProfileUseCase by inject()

    private var achivments: List<Achivment>? = null

    override fun attachView(view: ProfileView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(true)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadProfile()
        viewState.showIdeas(listOf(
            Idea(
                text = "Предлагаю по пятницам проводить встречи с обсуждением макетов приложений, чтобы каждый мог высказаться и поучаствовать в процессе.",
                answer = "Отличная идея, со следующего месяца перестроим процессы в команде."
            ),
            Idea(
                text = "Предлагаю по пятницам проводить встречи с обсуждением макетов приложений, чтобы каждый мог высказаться и поучаствовать в процессе.",
                answer = "Отличная идея, со следующего месяца перестроим процессы в команде."
            ),
        ))
    }

    private fun loadProfile() {
        launchUI(createEmptyHandler()) {
            viewState.toggleLoading(true)
            val user = withIO { getProfileUseCase() }
            achivments = user.achivements
            viewState.showProfile(user)
            viewState.toggleLoading(false)
        }
    }

    fun onOpenAchivments() {
        router?.navigateTo(AchivmentsScreen(achivments!!))
    }

    fun onBack() = router?.exit()
}