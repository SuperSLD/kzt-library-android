package com.example.app_ui.screens.main.documents.request

import com.example.app_domain.models.documents.Request
import online.juter.supersld.view.input.form.JTForm
import online.juter.supersld.view.input.form.JTFormPage
import online.juter.supersld.view.input.form.lines.CheckBoxLine
import online.juter.supersld.view.input.form.lines.SolidTextLine
import online.juter.supersld.view.input.form.lines.TextInputLine
import online.juter.supersld.view.input.form.lines.TextLine

class RequestMapperUi {

    fun map(request: Request): JTForm {
        val pages = request.pages.sortedBy { it.number }
        val formPages = pages.map { page ->
            val lines = page.lines.sortedBy { it.number }
            val formLines = lines.map { line ->
                when(line.type) {
                    "text" -> TextLine(line.content)
                    "input" -> TextInputLine(line.id, line.content, mandatory = line.required)
                    "input_big" -> TextInputLine(line.id, line.content, mandatory = line.required,
                        inputType = TextInputLine.TEXT_MULTILINE, minLines = 6
                    )
                    "info" -> SolidTextLine(line.content)
                    "checkbox" -> CheckBoxLine(line.id, text = line.content, mandatory = line.required)
                    else ->  TextLine(line.content)
                }
            }.toMutableList()
            JTFormPage(formLines, "Далее")
        }.toMutableList()

        return JTForm(
            name = request.title,
            pages = formPages,
            finishText = "Отправить заявку"
        )
    }
}