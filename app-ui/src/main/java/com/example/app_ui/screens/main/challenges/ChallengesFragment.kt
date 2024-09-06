package com.example.app_ui.screens.main.challenges

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.common.core.base.BaseFragment

class ChallengesFragment : BaseFragment(R.layout.fragment_challenges), MvpView {

    @InjectPresenter
    lateinit var presenter: ChallengesPresenter

    override fun onBackPressed() {
        presenter.onBack()
    }
}