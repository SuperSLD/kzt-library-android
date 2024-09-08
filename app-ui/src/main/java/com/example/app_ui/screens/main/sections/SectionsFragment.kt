package com.example.app_ui.screens.main.sections

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_domain.models.sections.Section
import com.example.app_ui.R
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopPadding
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.ext.setVisible
import com.example.app_ui.screens.main.sections.holders.SectionViewHolder
import kotlinx.android.synthetic.main.fragment_sections.content
import kotlinx.android.synthetic.main.fragment_sections.loading
import kotlinx.android.synthetic.main.fragment_sections.sections

class SectionsFragment : BaseFragment(R.layout.fragment_sections), SectionsView {

    @InjectPresenter
    lateinit var presenter: SectionsPresenter

    private val sectionsAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_section,
            { SectionViewHolder(it) },
            { item, _ -> presenter.onOpenSection(item) },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content.addSystemTopPadding()
        with(sections) {
            adapter = sectionsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun toggleLoading(show: Boolean) {
        content.setVisible(!show)
        loading.setVisible(show)
    }

    override fun showSections(list: List<Section>) {
        sectionsAdapter.swapItems(list)
    }
}