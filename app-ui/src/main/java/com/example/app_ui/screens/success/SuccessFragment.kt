package com.example.app_ui.screens.success

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.ext.getDrawable
import kotlinx.android.synthetic.main.fragment_success.*
import com.example.app_ui.common.core.base.BaseFragment

class SuccessFragment : BaseFragment(R.layout.fragment_success), SuccessView {

    companion object {
        fun newInstance(screen: SuccessScreen) = SuccessFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: SuccessPresenter

    @ProvidePresenter
    fun providePresenter() = SuccessPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as SuccessScreen,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        success_button.setOnClickListener {
            presenter.onConfirm()
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun setContent(title: Int, subtitle: Int, icon: Int, buttonTitle: Int) {
        success_icon.setImageDrawable(getDrawable(icon))
        success_title.setText(title)
        success_subtitle.setText(subtitle)
        success_button.setText(buttonTitle)
    }

}