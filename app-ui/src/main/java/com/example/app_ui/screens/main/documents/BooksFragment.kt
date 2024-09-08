package com.example.app_ui.screens.main.documents

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_domain.models.books.Book
import com.example.app_ui.R
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopPadding
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.ext.setVisible
import com.example.app_ui.screens.main.documents.holders.BigBooksViewHolder
import com.example.app_ui.screens.main.documents.holders.SmallBooksViewHolder
import kotlinx.android.synthetic.main.fragment_documents.books_recommendation_recycler
import kotlinx.android.synthetic.main.fragment_documents.books_switch
import kotlinx.android.synthetic.main.fragment_documents.documents_container
import kotlinx.android.synthetic.main.fragment_documents.documents_loading
import kotlinx.android.synthetic.main.fragment_documents.documents_nested
import kotlinx.android.synthetic.main.fragment_documents.my_books_recycler
import online.juter.supersld.view.input.selectors.JTHorizontalSwitch

class BooksFragment : BaseFragment(R.layout.fragment_documents), BooksView {

    @InjectPresenter
    lateinit var presenter: BooksPresenter

    private val myBooksAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_small_card_book,
            { SmallBooksViewHolder(it) },
            { item, _ -> presenter.onOpenBook(item) },
        )
    }

    private val recommendsAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_big_card_book,
            { BigBooksViewHolder(it) },
            { item, _ -> presenter.onOpenBook(item) },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        documents_container.addSystemTopPadding()

        with(my_books_recycler) {
            adapter = myBooksAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        with(books_recommendation_recycler) {
            adapter = recommendsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        with(books_switch) {
            init(
                tabs = listOf("Рекомендации", "Новинки"),
                params = JTHorizontalSwitch.JTSwitchParams(
                    textColorDefault = "#C9D0E3",
                    textColorSelected = "#FFFFFF",
                    corners = 12,
                    borderColor = "#F1F3F8",
                    backColorSelected = "#16545E",
                    backColor = "#F1F3F8",
                )
            )
            onTabChanged {
                presenter.onChangeTab(it)
            }
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showRecommendation(items: List<Book>) {
        recommendsAdapter.swapItems(items)
    }

    override fun showNewBooks(items: List<Book>) {
        recommendsAdapter.swapItems(items)
    }

    override fun showMyBooks(items: List<Book>) {
        myBooksAdapter.swapItems(items)
    }

    override fun toggleLoading(show: Boolean) {
        documents_loading.setVisible(show)
        documents_nested.setVisible(!show)
    }
}