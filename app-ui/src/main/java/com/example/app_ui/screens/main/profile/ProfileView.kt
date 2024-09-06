package com.example.app_ui.screens.main.profile

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.app_domain.models.Idea
import com.example.app_domain.models.user.User

interface ProfileView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showIdeas(items: List<Idea>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun toggleLoading(show: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProfile(user: User)
}
