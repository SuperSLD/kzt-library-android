package com.example.app_ui.screens.main.documents

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_domain.models.documents.Document
import com.example.app_domain.models.documents.Request
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.ext.setVisible
import com.example.app_ui.screens.main.documents.holders.DocumentViewHolder
import com.example.app_ui.screens.main.documents.holders.RequestViewHolder
import kotlinx.android.synthetic.main.fragment_documents.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopPadding

class DocumentsFragment : BaseFragment(R.layout.fragment_documents), DocumentsView {

    @InjectPresenter
    lateinit var presenter: DocumentsPresenter

    private val documentsAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_document,
            { DocumentViewHolder(it) },
            { item, _ -> presenter.onOpenDocument(item) },
        )
    }

    private val requestsAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_request,
            { RequestViewHolder(it) },
            { item, _ -> presenter.onOpenRequest(item) },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        documents_container.addSystemTopPadding()

        with(documents_recycler) {
            adapter = documentsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        with(documents_requests_recycler) {
            adapter = requestsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showDocument(items: List<Document>) {
        documentsAdapter.swapItems(items)
    }

    override fun showRequests(items: List<Request>) {
        requestsAdapter.swapItems(items)
    }

    override fun toggleLoading(show: Boolean) {
        documents_loading.setVisible(show)
        documents_nested.setVisible(!show)
    }
}