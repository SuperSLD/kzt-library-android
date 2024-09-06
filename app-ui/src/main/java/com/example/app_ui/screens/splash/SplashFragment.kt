package com.example.app_ui.screens.splash

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.common.core.base.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash), MvpView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onBackPressed() {
        presenter.onBack()
    }
}