package com.example.app_ui.screens.main.central

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_domain.models.central.Central
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.ext.setVisible
import kotlinx.android.synthetic.main.fragment_central.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopPadding

class CentralFragment : BaseFragment(R.layout.fragment_central), CentralView {

    @InjectPresenter
    lateinit var presenter: CentralPresenter

    private val adapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_news,
            { NewsViewHolder(it) },
            { item, _ ->  },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        central_container.addSystemTopPadding()

        with(central_news_recycler) {
            adapter = this@CentralFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        central_map_button.setOnClickListener {
            presenter.onOpenMap()
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showCentral(central: Central) {
        adapter.swapItems(central.listNews)
        central_coins.text = getString(R.string.central_coins, central.coins)
    }

    override fun toggleLoading(show: Boolean) {
        central_loading.setVisible(show)
        central_nested.setVisible(!show)
    }
}