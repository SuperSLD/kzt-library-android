package com.example.app_ui.screens.main.profile.holders

import android.view.View
import com.example.app_domain.models.user.Achivment
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.getDrawable
import kotlinx.android.synthetic.main.viewholder_achivment.view.*

class AchivmentViewHolder(containerView: View) : SimpleViewHolder<Achivment>(containerView) {

    private val achivmentUiMapper = AchivmentUiMapper()

    override fun bindTo(item: Achivment, pos: Int, onClickCallback: ((Achivment, Int) -> Unit)?) {
        with(containerView) {
            achivment_card.setOnClickListener { onClickCallback?.invoke(item, pos) }
            if (item.done) {
                achivment_icon.setImageDrawable(getDrawable(achivmentUiMapper.toOnIconRes(item.icon)))
            } else {
                achivment_icon.setImageDrawable(getDrawable(achivmentUiMapper.toOffIconRes(item.icon)))
            }
        }
    }
}