package com.example.app_ui.screens.main.documents.document

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.app_domain.models.books.Book
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopAndBottomPadding
import com.example.app_ui.common.core.base.toCalendar
import com.example.app_ui.ext.getColor
import com.example.app_ui.ext.hide
import com.example.app_ui.ext.setVisible
import com.example.app_ui.ext.show
import kotlinx.android.synthetic.main.fragment_document_detail.book_author
import kotlinx.android.synthetic.main.fragment_document_detail.book_auto_renewal_switch
import kotlinx.android.synthetic.main.fragment_document_detail.book_cover_image
import kotlinx.android.synthetic.main.fragment_document_detail.book_date
import kotlinx.android.synthetic.main.fragment_document_detail.book_description
import kotlinx.android.synthetic.main.fragment_document_detail.book_main_container
import kotlinx.android.synthetic.main.fragment_document_detail.book_name
import kotlinx.android.synthetic.main.fragment_document_detail.book_order_button
import kotlinx.android.synthetic.main.fragment_document_detail.book_rating
import kotlinx.android.synthetic.main.fragment_document_detail.book_renewal_button
import kotlinx.android.synthetic.main.fragment_document_detail.cvBack
import kotlinx.android.synthetic.main.fragment_document_detail.detail_book_loading
import kotlinx.android.synthetic.main.fragment_document_detail.detail_book_nested
import java.util.Calendar

class DocumentFragment : BaseFragment(R.layout.fragment_document_detail), DocumentView {

    companion object {
        fun newInstance(screen: DocumentScreen) = DocumentFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: DocumentPresenter

    @ProvidePresenter
    fun providePresenter() = DocumentPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as DocumentScreen,
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cvBack.setOnClickListener { onBackPressed() }
        book_renewal_button.setOnClickListener {
            presenter.renewBook()
        }
        book_order_button.setOnClickListener {
            presenter.orderBook()
        }

        book_main_container.addSystemTopAndBottomPadding()
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showBook(book: Book) {
        book_name.text = book.title
        book_author.text = book.author
        book_description.text = book.description
        book_rating.text = book.rating.toString()
        book_order_button.show()
        Glide.with(requireContext()).load(book.cover).into(book_cover_image)
        book.date?.let {
            val date = it.toCalendar()
            val currentCalendar = Calendar.getInstance()
            book_date.setTextColor(getColor(
                if (date.before(currentCalendar)) {
                    R.color.colorAlertRed
                } else R.color.colorTextBlack
            ))
            book_date.text = "До ${it.substring(0,5)}"
            book_renewal_button.show()
            book_order_button.hide()
            book_auto_renewal_switch.show()
            book_auto_renewal_switch.isChecked = book.renewal
        }
    }

    override fun showErrorToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun toggleLoading(show: Boolean) {
        detail_book_loading.setVisible(show)
        detail_book_nested.setVisible(!show)
    }

    override fun orderBook() {
        book_renewal_button.show()
        book_auto_renewal_switch.show()
        book_order_button.hide()
        Toast.makeText(context, "Книга забронирована", Toast.LENGTH_SHORT).show()
    }

    override fun renewBook(book: Book) {
        book.date?.let {
            val date = it.toCalendar()
            val currentCalendar = Calendar.getInstance()
            book_date.setTextColor(getColor(
                if (date.before(currentCalendar)) {
                    R.color.colorAlertRed
                } else R.color.colorTextBlack
            ))
            book_date.text = "До ${it.substring(0,5)}"
            Toast.makeText(context, "Книга успешно продлена", Toast.LENGTH_SHORT).show()
        }
    }
}