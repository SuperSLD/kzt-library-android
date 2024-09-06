package com.example.app_ui.screens.main.central.roadmap.selectpoint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.screens.main.central.roadmap.getMapColorData
import kotlinx.android.synthetic.main.fragment_selectpoint.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopAndBottomPadding

class SelectPointFragment : BaseFragment(R.layout.fragment_selectpoint), MvpView {

    companion object {
        const val ID = "id"
        const val TITLE_RES = "title_res"
        const val DESCRIPTION_RES = "description_res"
        const val SYMBOL = "symbol"
        const val COLOR = "color"

        fun createInstance(
            params: SelectPointScreen
        ) = SelectPointFragment().apply {
            arguments = Bundle()
            arguments?.putString(ID, params.id)
            arguments?.putInt(TITLE_RES, params.titleRes)
            arguments?.putInt(DESCRIPTION_RES, params.descriptionRes)
            arguments?.putString(SYMBOL, params.symbol)
            arguments?.putString(COLOR, params.color)
        }
    }

    @InjectPresenter
    lateinit var presenter: SelectPointPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuContainer.addSystemTopAndBottomPadding()
        icBack.setOnClickListener { onBackPressed() }

        initMapView()
        initMarkerData()
    }

    private fun initMapView() {
        with(mapView) {
            setColorData(getMapColorData())
            setMaxZoom(0.04773629F)
            hideFloors()
            init()
        }
    }

    private fun initMarkerData() {
        tvTitle.setText(arguments?.getInt(TITLE_RES)!!)
        tvDescription.setText(arguments?.getInt(DESCRIPTION_RES)!!)
        tvSymbol.text = arguments?.getString(SYMBOL)

        val symbolBack = ContextCompat.getDrawable(requireContext(), R.drawable.shape_orange_eclipse_48)
        DrawableCompat.setTint(symbolBack!!, Color.parseColor(arguments?.getString(COLOR)!!))
        tvSymbol.background = symbolBack

        with(markerView) {
            setSymbol(arguments?.getString(SYMBOL)!!)
            setColor(arguments?.getString(COLOR)!!)
            setTextColor("#FFFFFF")
            connect(mapView)
        }

        btnDone.setOnClickListener {
            presenter.onDoneClick(markerView.getMarker(arguments?.getString(ID)!!))
        }
    }

    override fun onBackPressed() {
        presenter.back()
    }
}