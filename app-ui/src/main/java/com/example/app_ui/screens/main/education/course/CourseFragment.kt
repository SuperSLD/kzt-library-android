package com.example.app_ui.screens.main.education.course

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_domain.models.courses.Course
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.ext.getColor
import com.example.app_ui.screens.main.education.course.holders.ChecklistViewHolder
import com.example.app_ui.screens.main.education.course.holders.LessonViewHolder
import kotlinx.android.synthetic.main.fragment_course.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding

class CourseFragment : BaseFragment(R.layout.fragment_course), CourseView {

    companion object {
        fun newInstance(screen: CourseScreen) = CourseFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: CoursePresenter

    @ProvidePresenter
    fun providePresenter() = CoursePresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as CourseScreen,
    )

    private val checklistAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_checklist,
            { ChecklistViewHolder(it) },
            { item, _ -> presenter.onOpenCheckListItem(item) },
        )
    }

    private val lessonsAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_lesson,
            { LessonViewHolder(it) },
            { item, _ -> presenter.onOpenLesson(item) },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        course_toolbar.init("", this::onBackPressed)
        course_nested.addSystemBottomPadding()
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showCourse(course: Course) {
        course_title.text = course.title
        course_description.text = course.description

        checklistAdapter.swapItems(course.checklistItems.sortedBy { it.number })
        lessonsAdapter.swapItems(course.lessons.sortedBy { it.number })

        with(course_progress) {
            setColor(getColor(R.color.colorPrimary))
            setColorText(getColor(R.color.colorPrimary))
            setEmptyColor(getColor(R.color.colorBorder))
            setMaxProgress(100)
//            if (progress.allListCount > 0) {
//                updateProgress((progress.finishedListCount / progress.allListCount.toFloat()) * 100)
//            } else updateProgress(0)
            updateProgress(course.percent())
            setCenterTextFormat("%.0f%%")
        }

        course_point_count.text = if (course.checklist) {
            course.checklistItems.count { it.checked }.toString()
        } else {
            course.lessons.count { it.finished }.toString()
        }

        course_point_count_description.text = getString(
            if(course.checklist) R.string.course_count_checks else R.string.course_count_lessons
        )

        with(course_recycler) {
            adapter = if (course.checklist) checklistAdapter else lessonsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}