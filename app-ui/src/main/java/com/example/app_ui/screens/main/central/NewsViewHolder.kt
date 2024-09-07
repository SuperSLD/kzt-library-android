package com.example.app_ui.screens.main.central

import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.app_domain.models.central.News
import com.example.app_domain.models.central.NewsType
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.hide
import com.example.app_ui.ext.setVisible
import com.example.app_ui.ext.show
import kotlinx.android.synthetic.main.viewholder_news.view.*

class NewsViewHolder(containerView: View) : SimpleViewHolder<News>(containerView) {

    override fun bindTo(item: News, pos: Int, onClickCallback: ((News, Int) -> Unit)?) {
        with(containerView) {
            news_card.setOnClickListener { onClickCallback?.invoke(item, pos) }
            news_title.text = item.title
            Glide.with(itemView.context).load(item.image).into(news_image)
            news_description.text = item.description
            when (item.type) {
                NewsType.EVENT.type -> {
                    news_event_info.show()
                    item.date?.let { news_event_date.text = it}
                }
                else -> {news_event_info.hide()}
            }
        }
    }
}