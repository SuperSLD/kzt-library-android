package com.example.app_ui.screens.main.education

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_domain.models.courses.Course
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.ext.setVisible
import kotlinx.android.synthetic.main.fragment_education.*
import online.juter.supersld.view.input.selectors.JTHorizontalSwitch
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopPadding

class EducationFragment : BaseFragment(R.layout.fragment_education), EducationView {

    @InjectPresenter
    lateinit var presenter: EducationPresenter

    private val newCourseAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_course,
            { CourseViewHolder(it) },
            { item, _ -> presenter.onOpenCourse(item) },
        )
    }

    private val courseAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_course,
            { CourseViewHolder(it) },
            { item, _ -> presenter.onOpenCourse(item) },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        education_container.addSystemTopPadding()

        with(education_new_recycler) {
            adapter = newCourseAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        with(education_recycler) {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(context)
        }

        with(education_switch) {
            init(
                tabs = listOf("Важные", "Допы", "Пройдены"),
                params = JTHorizontalSwitch.JTSwitchParams(
                    textColorDefault = "#C9D0E3",
                    textColorSelected = "#FFFFFF",
                    corners = 12,
                    borderColor = "#F1F3F8",
                    backColorSelected = "#16545E",
                    backColor = "#F1F3F8",
                )
            )
            onTabChanged {
                presenter.onChangeTab(it)
            }
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showNewCourses(items: List<Course>) {
        newCourseAdapter.swapItems(items)
    }

    override fun showFilteredCourses(items: List<Course>) {
        courseAdapter.swapItems(items)
    }

    override fun toggleLoading(show: Boolean) {
        education_loading.setVisible(show)
        education_nested.setVisible(!show)
    }
}