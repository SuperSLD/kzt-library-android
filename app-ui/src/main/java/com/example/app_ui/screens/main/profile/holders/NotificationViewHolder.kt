package com.example.app_ui.screens.main.profile.holders

import android.view.View
import com.example.app_domain.models.Notification
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import kotlinx.android.synthetic.main.viewholder_idea.view.*

class NotificationViewHolder(containerView: View) : SimpleViewHolder<Notification>(containerView) {

    override fun bindTo(item: Notification, pos: Int, onClickCallback: ((Notification, Int) -> Unit)?) {
        with(containerView) {
            idea_text.text = item.title
            idea_answer.text = item.text
        }
    }
}