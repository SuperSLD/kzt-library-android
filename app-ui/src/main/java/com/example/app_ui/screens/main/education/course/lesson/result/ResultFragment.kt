package com.example.app_ui.screens.main.education.course.lesson.result

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.ext.getColor
import com.example.app_ui.ext.getDrawable
import kotlinx.android.synthetic.main.fragment_result.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding

class ResultFragment : BaseFragment(R.layout.fragment_result), ResultView {

    companion object {
        fun newInstance(screen: ResultScreen) = ResultFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: ResultPresenter

    @ProvidePresenter
    fun providePresenter() = ResultPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as ResultScreen,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        result_toolbar.init("", this::onBackPressed)
        result_container.addSystemBottomPadding()
        result_return_button.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showResult(result: Pair<Int, Int>) {
        val success = result.first == result.second

        with(result_progress) {
            setColor(getColor(if (success) R.color.colorPositiveGreen else R.color.colorAlertRed))
            setColorText(getColor(if (success) R.color.colorPositiveGreen else R.color.colorAlertRed))
            setEmptyColor(getColor(R.color.colorBorder))
            setMaxProgress(100)
            updateProgress((result.first.toFloat() / result.second.toFloat()) * 100F)
            setCenterTextFormat("%.0f%%")
        }

        if (success) {
            result_count.text = result.second.toString()
            result_count_description.setText(R.string.lesson_success_count)
            result_description.setText(R.string.lesson_success)
            result_icon.setImageDrawable(getDrawable(R.drawable.ic_lesson_success))
        } else {
            result_count.text = (result.second - result.first).toString()
            result_count_description.setText(R.string.lesson_failed_count)
            result_description.setText(R.string.lesson_failed)
            result_icon.setImageDrawable(getDrawable(R.drawable.ic_lesson_failed))
        }
    }
}