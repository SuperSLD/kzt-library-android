package com.example.app_domain.models.courses

data class Course(
    val id: String,
    val tag: String = "NONE",
    val title: String,
    val new: Boolean,
    val important: Boolean,
    val checklist: Boolean,
    val description: String,
    val lessons: List<Lesson>,
    val checklistItems: List<CheckListItem>,
) {

    fun percent() = if (checklist) {
            checklistItems.count { it.checked } * 100 / checklistItems.size
        } else {
            lessons.count { it.finished } * 100 / lessons.size
        }
}

data class CheckListItem(
    val id: String,
    val title: String,
    val description: String,
    var checked: Boolean = false,
    val number: Int,
)

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val pages: List<LessonPage>,
    var finished: Boolean = false,
    val number: Int,
)

data class LessonPage(
    val id: String,
    val description: String,
    val image: String,
    val answer: Boolean,
    val correctAnswer: String,
    val correctAnswerId: String?,
    val answers: List<LessonPageAnswer>,
    val number: Int,
)

data class LessonPageAnswer(
    val id: String,
    val title: String,
)