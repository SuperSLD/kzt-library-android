package com.example.app_ui.screens.main.education

import android.annotation.SuppressLint
import android.view.View
import com.example.app_domain.models.courses.Course
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.setVisible
import kotlinx.android.synthetic.main.viewholder_course.view.*
import kotlinx.android.synthetic.main.viewholder_news.view.*

class CourseViewHolder(containerView: View) : SimpleViewHolder<Course>(containerView) {

    @SuppressLint("SetTextI18n")
    override fun bindTo(item: Course, pos: Int, onClickCallback: ((Course, Int) -> Unit)?) {
        with(containerView) {
            education_title.text = item.title
            education_important.setVisible(item.important)
            education_new.setVisible(item.new, View.INVISIBLE)
            education_tag.text = item.tag
            education_percent.text = "${item.percent()}%"

            course_card.setOnClickListener { onClickCallback?.invoke(item, pos) }
        }
    }
}