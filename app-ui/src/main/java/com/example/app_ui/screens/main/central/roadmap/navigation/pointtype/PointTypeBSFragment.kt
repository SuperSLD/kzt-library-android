package com.example.app_ui.screens.main.central.roadmap.navigation.pointtype

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.common.MvpBottomSheetDialogFragment
import kotlinx.android.synthetic.main.bs_point_type.btnMap
import kotlinx.android.synthetic.main.bs_point_type.btnQr
import kotlinx.android.synthetic.main.bs_point_type.btnRoom

class PointTypeBSFragment : MvpBottomSheetDialogFragment(R.layout.bs_point_type), MvpView {

    @InjectPresenter
    lateinit var presenter: PointTypeBSPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMap.setOnClickListener {
            presenter.mapPoint()
            dismiss()
        }
        btnRoom.setOnClickListener {
            presenter.roomPoint()
            dismiss()
        }
        btnQr.setOnClickListener {
            presenter.qrPoint()
            dismiss()
        }
    }
}