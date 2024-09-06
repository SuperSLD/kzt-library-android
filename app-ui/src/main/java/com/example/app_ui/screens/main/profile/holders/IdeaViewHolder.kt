package com.example.app_ui.screens.main.profile.holders

import android.view.View
import com.example.app_domain.models.Idea
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import kotlinx.android.synthetic.main.viewholder_idea.view.*

class IdeaViewHolder(containerView: View) : SimpleViewHolder<Idea>(containerView) {

    override fun bindTo(item: Idea, pos: Int, onClickCallback: ((Idea, Int) -> Unit)?) {
        with(containerView) {
            idea_text.text = item.text
            idea_answer.text = item.answer
        }
    }
}