package com.example.app_ui.screens.main.education.course.lesson

import com.example.app_domain.models.courses.Lesson
import online.juter.supersld.view.input.form.JTForm
import online.juter.supersld.view.input.form.JTFormPage
import online.juter.supersld.view.input.form.lines.RadioItem
import online.juter.supersld.view.input.form.lines.RadioLine
import online.juter.supersld.view.input.form.lines.TextLine

class LessonMapperUi {

    fun map(lesson: Lesson): JTForm {
        val pages = lesson.pages.sortedBy { it.number }
        val formPages = pages.map { page ->
            if (page.answer) {
                JTFormPage(lines = listOfNotNull(
                    if (page.image.isNotEmpty()) ImageLine(page.image) else null,
                    TextLine(page.description),
                    RadioLine(
                        id = page.id,
                        list = page.answers.map{ RadioItem(it.id, it.title) }.toMutableList(),
                        title = "Выберите правильный ответ:",
                        mandatory = false,
                        correctId = page.correctAnswerId,
                    )
                ).toMutableList(),"Далее")
            } else {
                JTFormPage(lines = listOfNotNull(
                    if (page.image.isNotEmpty()) ImageLine(page.image) else null,
                    TextLine(page.description),
                ).toMutableList(),"К вопросам")
            }
        }.toMutableList()

        return JTForm(
            name = lesson.title,
            pages = formPages,
            finishText = "Завершить"
        )
    }
}