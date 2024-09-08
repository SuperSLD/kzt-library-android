package com.example.app_ui.screens.main.sections.holders

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.example.app_domain.models.sections.Section
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import kotlinx.android.synthetic.main.viewholder_section.view.card
import kotlinx.android.synthetic.main.viewholder_section.view.image
import kotlinx.android.synthetic.main.viewholder_section.view.subtitle
import kotlinx.android.synthetic.main.viewholder_section.view.title

class SectionViewHolder(containerView: View) : SimpleViewHolder<Section>(containerView) {

    override fun bindTo(item: Section, pos: Int, onClickCallback: ((Section, Int) -> Unit)?) {
        with(containerView) {
            title.text = item.title
            subtitle.text = item.subtitle
            Glide.with(itemView.context).load(item.cover).into(image)
            card.setOnClickListener {
                onClickCallback?.invoke(item, pos)
            }
        }
    }
}