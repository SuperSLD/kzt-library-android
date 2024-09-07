package com.example.app_ui.screens.main.profile.holders

import android.annotation.SuppressLint
import android.view.View
import com.example.app_domain.models.Notification
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.setVisible
import kotlinx.android.synthetic.main.viewholder_idea.view.*

class NotificationViewHolder(containerView: View) : SimpleViewHolder<Notification>(containerView) {

    @SuppressLint("SetTextI18n")
    override fun bindTo(item: Notification, pos: Int, onClickCallback: ((Notification, Int) -> Unit)?) {
        with(containerView) {
            idea_text.text = item.title
            if (item.type == "event") {
                idea_answer.text = item.text + " Мероприятие: " + item.event?.title
            } else {
                idea_answer.text = item.text
            }
        }
    }
}