package com.example.app_ui.screens.main.central.roadmap

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.ext.*
import kotlinx.android.synthetic.main.fragment_road_map.*
import online.jutter.roadmapview.data.models.map.RMMarker
import online.jutter.roadmapview.data.models.nav.RMNavigationData
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding
import com.example.app_ui.common.core.base.addSystemTopPadding
import java.math.RoundingMode

class RoadMapFragment : BaseFragment(R.layout.fragment_road_map), RoadMapView {

    @InjectPresenter
    lateinit var presenter: RoadMapPresenter

    private var navData: RMNavigationData? = null
    private var currentStep = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cvBack.setOnClickListener { onBackPressed() }
        vgButtonContainer.addSystemTopPadding()
        navButtonContainer.addSystemBottomPadding()
        navLoadingContainer.addSystemBottomPadding()
        navResultContainer.addSystemBottomPadding()
        navStepsContainer.addSystemBottomPadding()
        btnNextStep.setOnClickListener { nextStep() }
        btnPreviousStep.setOnClickListener { previousStep() }
        icCloseNav.setOnClickListener { hideNavData() }
        icCloseStepsNav.setOnClickListener { hideNavData() }
        btnShowSteps.setOnClickListener {
            currentStep = 1
            showStep(currentStep)
            showState(ScreenState.NAV_STEPS)
        }
        btnNavigation.setOnClickListener { presenter.onOpenNavPoints() }
        toggleMapLoading(true)
        initMapView()
    }

    private fun initMapView() {
        with(mapView) {
            setColorData(getMapColorData())
            setMaxZoom(0.04773629F)
            setStructureZoom(0.006428921F)
            onLoad { toggleMapLoading(false) }
            onLoadError { showErrorMapLoading() }
            onFloorCountUpdate {
                floorsView?.setFloors(it)
                floorsView?.onFloorChanged { f -> mapView.setCurrentFloor(f) }
            }
            onNavigationLoad { navData ->
                if (navData != null) {
                    this@RoadMapFragment.navData = navData
                    showState(ScreenState.NAV_RESULT)
                    showNavData(navData)
                } else {
                    hideNavData()
                    Toast.makeText(context, getString(R.string.roadmap_navigation_error), Toast.LENGTH_SHORT).show()
                }
            }
            init()
        }
    }

    private fun showNavData(navData: RMNavigationData) {


        tvDistance.text = getString(R.string.roadmap_distance, (navData.sumDist/1000).round(1).toString())
        tvTime.text = getString(R.string.roadmap_time, navData.sumMin.toInt().toString())

        with(vStepProgress) {
            setMaxProgress(navData.steps.size)
            setProgress(1)
            setColor(ContextCompat.getColor(context, R.color.colorPrimary))
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackSecondary))
        }
    }

    private fun previousStep() {
        currentStep--
        if (currentStep == 0) {
            mapView.showRoad(navData!!, false)
            showState(ScreenState.NAV_RESULT)
            return
        }
        showStep(currentStep)
    }

    private fun nextStep() {
        currentStep++
        if (currentStep > navData!!.steps.size) {
            hideNavData()
            return
        }
        showStep(currentStep)
    }

    private fun showStep(step: Int) {
        if (currentStep == navData!!.steps.size) {
            btnNextStep.setText(R.string.roadmap_steps_finish)
        } else {
            btnNextStep.setText(R.string.roadmap_steps_next)
        }
        mapView.showRoad(navData!!.getStepRoad(step - 1), false)
        (navData!!.steps[step-1].floor?:1).let {
            mapView.setCurrentFloor(it)
            floorsView.setSelectedFloor(it)
        }
        vStepProgress.updateProgress(step)
        val nextStepFloor = if (step <= navData!!.steps.lastIndex) navData!!.steps[step].floor else null
        tvStepsDescription.text = navData!!.steps[step - 1].toText(nextStepFloor)
    }

    private fun hideNavData() {
        showState(ScreenState.NAV_BUTTON)
        mapView.clearNavigation()
        presenter.clearNavData()
    }

    private fun showErrorMapLoading() {
        mapContainer.show()
    }

    private fun toggleMapLoading(show: Boolean) {
        roadmap_loading.setVisible(show)
        mapContainer.setVisible(!show)
    }

    private fun showState(screenState: ScreenState) {
        when(screenState) {
            ScreenState.NAV_BUTTON -> {
                navLoadingContainer.collapse()
                navResultContainer.collapse()
                navStepsContainer.collapse()
                navButtonContainer.expand()
            }
            ScreenState.NAV_LOADING -> {
                navLoadingContainer.expand()
                navResultContainer.collapse()
                navStepsContainer.collapse()
                navButtonContainer.collapse()
            }
            ScreenState.NAV_RESULT -> {
                navLoadingContainer.collapse()
                navResultContainer.expand()
                navStepsContainer.collapse()
                navButtonContainer.collapse()
            }
            ScreenState.NAV_STEPS -> {
                navLoadingContainer.collapse()
                navResultContainer.collapse()
                navStepsContainer.expand()
                navButtonContainer.collapse()
            }
        }
    }

    override fun onBackPressed() {
        presenter.back()
    }

    override fun findRoad(markers: Pair<RMMarker, RMMarker>) {
        navButtonContainer.hide()
        mapView.findRoad(markers.first, markers.second)
        tvNavStart.text = markers.first.name
        tvNavEnd.text = markers.second.name
        tvNavStepsStart.text = markers.first.name
        tvNavStepsEnd.text = markers.second.name
        showState(ScreenState.NAV_LOADING)
    }
}

enum class ScreenState {
    NAV_BUTTON,
    NAV_LOADING,
    NAV_RESULT,
    NAV_STEPS,
}

fun Double.round(n: Int) = this.toBigDecimal().setScale(n, RoundingMode.CEILING).toDouble()