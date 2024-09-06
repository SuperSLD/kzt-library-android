package com.example.app_ui.screens.main.education.course.lesson

import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.usecases.courses.FinishUseCase
import com.example.app_ui.ext.createHandler
import com.example.app_ui.screens.main.education.course.lesson.result.ResultScreen
import online.juter.supersld.view.input.form.JTForm
import online.juter.supersld.view.input.form.lines.RadioLine
import online.jutter.supersld.common.base.BasePresenter
import com.example.app_ui.common.core.base.launchUI
import com.example.app_ui.common.core.base.withIO
import org.koin.core.inject

@InjectViewState
class LessonPresenter(
    private val params: LessonScreen,
) : BasePresenter<LessonView>() {

    private val bottomVisibilityController: BottomVisibilityController by inject()
    private val finishUseCase: FinishUseCase by inject()

    override fun attachView(view: LessonView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val mapper = LessonMapperUi()
        viewState.showForm(form = mapper.map(params.lesson))
    }

    fun onFinish(form: JTForm) {
        launchUI(createHandler {
            it.printStackTrace()
            viewState.toggleLoading(false)
        }) {
            viewState.toggleLoading(true)
            val result = validate(form)
            if (result.first == result.second) {
                withIO { finishUseCase(params.lesson.id) }
                params.lesson.finished = true
            }
            router?.replaceScreen(ResultScreen(result))
        }
    }

    private fun validate(form: JTForm): Pair<Int, Int> {
        var validCount = 0
        form.pages.forEach { page ->
            page.lines.forEach { line ->
                if (line is RadioLine && line.correctVariant()) validCount++
            }
        }
        return Pair(validCount, params.lesson.pages.count { it.answer })
    }

    fun onBack() = router?.exit()
}