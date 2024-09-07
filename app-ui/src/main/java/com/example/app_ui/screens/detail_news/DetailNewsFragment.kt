package com.example.app_ui.screens.detail_news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.app_domain.models.central.News
import com.example.app_domain.models.central.NewsType
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopPadding
import com.example.app_ui.ext.setVisible
import com.example.app_ui.ext.show
import kotlinx.android.synthetic.main.fragment_achivments.nested
import kotlinx.android.synthetic.main.fragment_detail_news.detail_news_loading
import kotlinx.android.synthetic.main.fragment_detail_news.detail_news_nested
import kotlinx.android.synthetic.main.fragment_detail_news.news_description
import kotlinx.android.synthetic.main.fragment_detail_news.news_event_date
import kotlinx.android.synthetic.main.fragment_detail_news.news_image
import kotlinx.android.synthetic.main.fragment_detail_news.news_layout_date_info
import kotlinx.android.synthetic.main.fragment_detail_news.news_subscribe_button
import kotlinx.android.synthetic.main.fragment_detail_news.news_title

class DetailNewsFragment: BaseFragment(R.layout.fragment_detail_news), DetailNewsView {

    companion object {
        fun newInstance(screen: DetailNewsScreen) = DetailNewsFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: DetailNewsPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detail_news_nested.addSystemTopPadding()

        news_subscribe_button.setOnClickListener {
            presenter.subscribeOnEvent()
        }
    }
    @ProvidePresenter
    fun providePresenter() = DetailNewsPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as DetailNewsScreen,
    )

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showEventInfo(news: News) {
        Glide.with(requireContext()).load(news.image).into(news_image)
        news_title.text = news.title
        news_description.text = news.description

        if (news.type == NewsType.EVENT.type) {
            news_layout_date_info.show()
            news_subscribe_button.show()
            news_event_date.text = news.date
        }
    }

    override fun toggleLoading(show: Boolean) {
        detail_news_loading.setVisible(show)
        detail_news_nested.setVisible(!show)
    }

    override fun showErrorToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}