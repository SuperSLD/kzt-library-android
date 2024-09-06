package com.example.app_ui.screens.main.education.course.lesson

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.ext.setVisible
import com.example.app_ui.getMainActivity
import kotlinx.android.synthetic.main.fragment_lesson.*
import online.juter.supersld.view.input.form.JTForm
import online.juter.supersld.view.input.form.JTFormParams
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding

class LessonFragment : BaseFragment(R.layout.fragment_lesson), LessonView {

    companion object {
        fun newInstance(screen: LessonScreen) = LessonFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: LessonPresenter

    @ProvidePresenter
    fun providePresenter() = LessonPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as LessonScreen,
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showForm(form: JTForm) {
        lesson_toolbar.init(
            form.name!!,
            back = this::onBackPressed
        )

        with(lesson_form) {
            init(
                form = form,
                childFragmentManager = getMainActivity().supportFragmentManager,
                createFormParams()
            )
            onFinish(presenter::onFinish)
            onToast {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            addSystemBottomPadding()
        }
    }

    private fun createFormParams() =
        JTFormParams().apply {
            colorAccent = Color.parseColor("#7046DC")
            colorBorder = Color.parseColor("#E6EBF0")
            colorTextPrimary = Color.parseColor("#292929")
            colorTextSecondary = Color.parseColor("#C9D0E3")

            borderBackground = R.drawable.shape_border

            buttonSolidStyle = R.style.ButtonSolid
            buttonEmptyStyle = R.style.ButtonEmpty
        }

    override fun toggleLoading(show: Boolean) {
        lesson_loading.setVisible(show)
        lesson_container.setVisible(!show)
    }
}