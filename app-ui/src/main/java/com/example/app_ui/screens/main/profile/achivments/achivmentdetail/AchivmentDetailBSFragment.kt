package com.example.app_ui.screens.main.profile.achivments.achivmentdetail

import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_domain.models.user.Achivment
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.MvpBottomSheetDialogFragment
import com.example.app_ui.ext.getDrawable
import com.example.app_ui.screens.main.profile.holders.AchivmentUiMapper
import kotlinx.android.synthetic.main.bs_achivment.*

class AchivmentDetailBSFragment : MvpBottomSheetDialogFragment(R.layout.bs_achivment), AchivmentDetailBSView {

    companion object {
        fun newInstance(screen: Achivment) = AchivmentDetailBSFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: AchivmentDetailBSPresenter

    @ProvidePresenter
    fun providePresenter() = AchivmentDetailBSPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as Achivment,
    )

    override fun showAchivment(achivment: Achivment) {
        val achivmentUiMapper = AchivmentUiMapper()
        if (achivment.done) {
            bs_achivments_icon.setImageDrawable(getDrawable(achivmentUiMapper.toOnIconRes(achivment.icon)))
        } else {
            bs_achivments_icon.setImageDrawable(getDrawable(achivmentUiMapper.toOffIconRes(achivment.icon)))
        }
        bs_achivments_title.text = achivment.title
        bs_achivments_description.text = achivment.description
    }
}