package com.example.app_ui.screens.main.education.course.holders

import android.view.View
import com.example.app_domain.models.courses.CheckListItem
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.getDrawable
import kotlinx.android.synthetic.main.viewholder_checklist.view.*

class ChecklistViewHolder(containerView: View) : SimpleViewHolder<CheckListItem>(containerView) {

    override fun bindTo(item: CheckListItem, pos: Int, onClickCallback: ((CheckListItem, Int) -> Unit)?) {
        with(containerView) {
            checklist_title.text = item.title
            checklist_icon.setImageDrawable(
                getDrawable(if(item.checked) R.drawable.ic_checked else R.drawable.ic_unchecked)
            )
            checklist_card.setOnClickListener { onClickCallback?.invoke(item, pos) }
        }
    }
}