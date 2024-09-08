package com.example.app_ui.screens.main.documents.holders

import android.view.View
import com.bumptech.glide.Glide
import com.example.app_domain.models.books.Book
import com.example.app_ui.common.view.simplerecycler.SimpleViewHolder
import kotlinx.android.synthetic.main.viewholder_big_card_book.view.big_book_layout
import kotlinx.android.synthetic.main.viewholder_big_card_book.view.book_author
import kotlinx.android.synthetic.main.viewholder_big_card_book.view.book_cover_image
import kotlinx.android.synthetic.main.viewholder_big_card_book.view.book_rating
import kotlinx.android.synthetic.main.viewholder_big_card_book.view.book_title

class BigBooksViewHolder(container: View): SimpleViewHolder<Book>(container) {
    override fun bindTo(item: Book, pos: Int, onClickCallback: ((Book, Int) -> Unit)?) {
        with(containerView) {
            Glide.with(itemView.context).load(item.cover).into(book_cover_image)
            book_title.text = item.title
            book_author.text = item.author
            book_rating.text = if (item.rating != -1f) item.rating.toString() else "?"

            big_book_layout.setOnClickListener { onClickCallback?.invoke(item, pos) }
        }
    }
}