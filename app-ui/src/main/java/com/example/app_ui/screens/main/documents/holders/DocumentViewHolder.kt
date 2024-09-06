package com.example.app_ui.screens.main.documents.holders

import android.view.View
import com.example.app_domain.models.documents.Document
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.setVisible
import kotlinx.android.synthetic.main.viewholder_document.view.*

class DocumentViewHolder(containerView: View) : SimpleViewHolder<Document>(containerView) {

    override fun bindTo(item: Document, pos: Int, onClickCallback: ((Document, Int) -> Unit)?) {
        with(containerView) {
            document_title.text = item.title
            document_deadline.text = resources.getString(R.string.documents_deadline, item.deadline)
            document_need_sign.setVisible(true)

            document_card.setOnClickListener { onClickCallback?.invoke(item, pos) }
        }
    }
}