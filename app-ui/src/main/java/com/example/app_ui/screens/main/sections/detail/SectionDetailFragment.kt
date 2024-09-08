package com.example.app_ui.screens.main.sections.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.app_domain.models.sections.Section
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopAndBottomPadding
import com.example.app_ui.common.core.base.addSystemTopPadding
import kotlinx.android.synthetic.main.fragment_section_detail.author
import kotlinx.android.synthetic.main.fragment_section_detail.avatarImage
import kotlinx.android.synthetic.main.fragment_section_detail.cvBack
import kotlinx.android.synthetic.main.fragment_section_detail.description
import kotlinx.android.synthetic.main.fragment_section_detail.image
import kotlinx.android.synthetic.main.fragment_section_detail.linkButton
import kotlinx.android.synthetic.main.fragment_section_detail.nested
import kotlinx.android.synthetic.main.fragment_section_detail.schedule
import kotlinx.android.synthetic.main.fragment_section_detail.title

class SectionDetailFragment : BaseFragment(R.layout.fragment_section_detail), SectionDetailView {

    companion object {
        fun newInstance(screen: SectionDetailScreen) = SectionDetailFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: SectionDetailPresenter

    @ProvidePresenter
    fun providePresenter() = SectionDetailPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as SectionDetailScreen,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cvBack.setOnClickListener { onBackPressed() }
        nested.addSystemTopAndBottomPadding()
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showSection(section: Section) {
        Glide.with(image.context).load(section.cover).into(image)
        title.text = section.title
        description.text = section.description
        schedule.text = section.schedule
        Glide.with(avatarImage.context).load(section.authorAvatar).into(avatarImage)
        author.text = section.author
        linkButton.setOnClickListener {  }
    }
}