package com.example.app_ui.screens.main.profile.achivments.achivmentdetail

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.models.user.Achivment
import online.jutter.supersld.common.base.BasePresenter

@InjectViewState
class AchivmentDetailBSPresenter(
    val params: Achivment,
) : BasePresenter<AchivmentDetailBSView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showAchivment(params)
    }
}