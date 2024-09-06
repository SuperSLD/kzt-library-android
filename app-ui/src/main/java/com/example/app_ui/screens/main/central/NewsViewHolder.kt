package com.example.app_ui.screens.main.central

import android.view.View
import com.bumptech.glide.Glide
import com.example.app_domain.models.central.News
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import kotlinx.android.synthetic.main.viewholder_news.view.*

class NewsViewHolder(containerView: View) : SimpleViewHolder<News>(containerView) {

    override fun bindTo(item: News, pos: Int, onClickCallback: ((News, Int) -> Unit)?) {
        with(containerView) {
            news_title.text = item.title
            Glide.with(itemView.context).load(item.image).into(news_image)
            news_description.text = item.description
        }
    }
}