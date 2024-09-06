package com.example.app_ui.screens.main.education.course.checkitem

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_domain.models.courses.CheckListItem
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.ext.getDrawable
import com.example.app_ui.ext.setVisible
import kotlinx.android.synthetic.main.fragment_check_item.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding

class CheckItemFragment : BaseFragment(R.layout.fragment_check_item), CheckItemView {

    companion object {
        fun newInstance(screen: CheckItemScreen) = CheckItemFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: CheckItemPresenter

    @ProvidePresenter
    fun providePresenter() = CheckItemPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as CheckItemScreen,
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        check_item_toolbar.init("", this::onBackPressed)
        check_item_container.addSystemBottomPadding()
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showCheckItem(checkListItem: CheckListItem) {
        check_item_title.text = checkListItem.title
        check_item_description.text = checkListItem.description
        check_item_icon.setImageDrawable(
            getDrawable(if(checkListItem.checked) R.drawable.ic_checked else R.drawable.ic_unchecked_full)
        )
        check_item_button.setVisible(!checkListItem.checked)
        check_item_button.setOnClickListener { presenter.onCheck() }
        check_item_button_back.setVisible(checkListItem.checked)
        check_item_button_back.setOnClickListener { onBackPressed() }
    }

    override fun toggleLoading(show: Boolean) {
        check_item_loading.setVisible(show)
        check_item_container.setVisible(!show)
    }
}