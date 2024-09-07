package com.example.app_ui.screens.detail_news.success_subscribe

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CalendarContract.EXTRA_EVENT_BEGIN_TIME
import android.provider.CalendarContract.Events
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_domain.models.central.News
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_success_subscribe.add_to_calendar_button
import kotlinx.android.synthetic.main.fragment_success_subscribe.success_button
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SuccessSubscribeFragment: BaseFragment(R.layout.fragment_success_subscribe), SuccessSubscribeView {

    companion object {
        fun newInstance(screen: SuccessSubscribeScreen) = SuccessSubscribeFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: SuccessSubscribePresenter

    @ProvidePresenter
    fun providePresenter() = SuccessSubscribePresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as SuccessSubscribeScreen,
    )

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ARG_KEY_SCREEN_PARAMS, presenter.getNews())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        success_button.setOnClickListener {
            presenter.onBack()
        }

        add_to_calendar_button.setOnClickListener {
            presenter.onClickToAddToCalendar()
        }
    }

    override fun addEventToCalendar(news: News) {
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val date = news.date?.let { format.parse(it) } ?: Date()
        val startMillis: Long = date.time

        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = Events.CONTENT_URI
            putExtra(EXTRA_EVENT_BEGIN_TIME, startMillis)
            putExtra(Events.TITLE, news.title)
            putExtra(Events.DESCRIPTION, news.description)
            putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        requireActivity().startActivity(intent)
    }
}