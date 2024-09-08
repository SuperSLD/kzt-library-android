package com.example.app_ui.screens.main.documents.holders

import android.view.View
import com.bumptech.glide.Glide
import com.example.app_domain.models.books.Book
import com.example.app_ui.R
import com.example.app_ui.common.core.base.toCalendar
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import com.example.app_ui.ext.getColor
import kotlinx.android.synthetic.main.viewholder_small_card_book.view.book_date
import kotlinx.android.synthetic.main.viewholder_small_card_book.view.small_book_cover
import kotlinx.android.synthetic.main.viewholder_small_card_book.view.small_book_rating
import kotlinx.android.synthetic.main.viewholder_small_card_book.view.small_card_book
import java.util.Calendar

class SmallBooksViewHolder(container: View): SimpleViewHolder<Book>(container) {
    override fun bindTo(item: Book, pos: Int, onClickCallback: ((Book, Int) -> Unit)?) {
        with(containerView) {
            Glide.with(itemView.context).load(item.cover).into(small_book_cover)
            val date = item.date!!.toCalendar()
            val currentCalendar = Calendar.getInstance()
            book_date.setTextColor(getColor(
                if (date.before(currentCalendar)) {
                    R.color.colorAlertRed
                } else R.color.colorTextBlack
            ))

            small_book_rating.text = if (item.rating != -1f) item.rating.toString() else "?"
            book_date.text = "До ${item.date!!.substring(0,5)}"
            small_card_book.setOnClickListener { onClickCallback?.invoke(item, pos) }
        }
    }
}