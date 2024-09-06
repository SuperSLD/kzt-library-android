package com.example.app_ui.screens.main.central.roadmap.selectroom

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import kotlinx.android.synthetic.main.fragment_selectroom.*
import online.jutter.roadmapview.RMDataController
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding
import com.example.app_ui.common.core.base.addSystemTopAndBottomPadding

class SelectRoomFragment : BaseFragment(R.layout.fragment_selectroom), MvpView {

    private val adapter by lazy { RoomsAdapter(presenter::onRoomClick) }

    @InjectPresenter
    lateinit var presenter: SelectRoomPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listContainer.addSystemTopAndBottomPadding()
        rvRooms.addSystemBottomPadding()
        btnBack.setOnClickListener { onBackPressed() }

        initRoomsRecycler()
        initSearch()
    }

    private fun initSearch() {
        with(vSearch) {
            setHintString(context.getString(R.string.roadmap_search_room))
            setClearIcon(R.drawable.ic_clear_line)
            onSearch { adapter.setSearchFilter(it) }
            init()
        }
    }

    private fun initRoomsRecycler() {
        with(rvRooms) {
            adapter = this@SelectRoomFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
        adapter.addData(RMDataController.create("")!!.getMapFromCache()!!.getRooms())
    }

    override fun onBackPressed() {
        presenter.back()
    }
}