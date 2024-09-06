package com.example.app_ui.screens.main.documents.document

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_domain.models.documents.Document
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import kotlinx.android.synthetic.main.fragment_document_detail.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding

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

        document_container.addSystemBottomPadding()
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showDocument(document: Document) {
        document_toolbar.init(title = "", back = this::onBackPressed)
        document_title.text = document.title
        document_card.setOnClickListener {  }
        document_sign_button.setOnClickListener {  }
    }
}