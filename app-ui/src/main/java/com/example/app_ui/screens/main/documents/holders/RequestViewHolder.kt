package com.example.app_ui.screens.main.documents.holders

import android.view.View
import com.example.app_domain.models.documents.Request
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.getDrawable
import kotlinx.android.synthetic.main.viewholder_request.view.*

class RequestViewHolder(containerView: View) : SimpleViewHolder<Request>(containerView) {

    override fun bindTo(item: Request, pos: Int, onClickCallback: ((Request, Int) -> Unit)?) {
        with(containerView) {
            request_card.setOnClickListener { onClickCallback?.invoke(item, pos) }
            request_icon.setImageDrawable(getDrawable(item.iconRes()))
            request_title.text = item.title
        }
    }
}

fun Request.iconRes() = when(icon) {
    "otpusk" -> R.drawable.ic_deck
    "komandirovka" -> R.drawable.ic_luggage
    "zakupka" -> R.drawable.ic_shopping_cart
    "obrazovanie" -> R.drawable.ic_school_req
    else -> R.drawable.ic_lock
}