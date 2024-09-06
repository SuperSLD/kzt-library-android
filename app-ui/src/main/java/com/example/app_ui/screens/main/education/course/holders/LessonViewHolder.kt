package com.example.app_ui.screens.main.education.course.holders

import android.view.View
import com.example.app_domain.models.courses.CheckListItem
import com.example.app_domain.models.courses.Lesson
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.getDrawable
import kotlinx.android.synthetic.main.viewholder_checklist.view.*
import kotlinx.android.synthetic.main.viewholder_lesson.view.*

class LessonViewHolder(containerView: View) : SimpleViewHolder<Lesson>(containerView) {

    override fun bindTo(item: Lesson, pos: Int, onClickCallback: ((Lesson, Int) -> Unit)?) {
        with(containerView) {
            lesson_title.text = item.title
            lesson_description.text = item.description
            lesson_count.text = resources.getString(R.string.course_count_lesson_count, item.pages.count { it.answer })
            lesson_icon.setImageDrawable(
                getDrawable(if(item.finished) R.drawable.ic_checked else R.drawable.ic_unchecked)
            )
            lesson_card.setOnClickListener { onClickCallback?.invoke(item, pos) }
        }
    }
}