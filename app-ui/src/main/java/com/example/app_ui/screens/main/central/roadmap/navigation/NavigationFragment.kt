package com.example.app_ui.screens.main.central.roadmap.navigation
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.screens.main.central.roadmap.getMapColorData
import com.example.app_ui.screens.main.central.roadmap.navigation.pointtype.PointTypeBSFragment
import kotlinx.android.synthetic.main.fragment_nav.*
import online.jutter.roadmapview.data.models.map.RMMarker
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopAndBottomPadding
import online.jutter.roadmapview.extensions.createColor

class NavigationFragment : BaseFragment(R.layout.fragment_nav), NavigationView {

    @InjectPresenter
    lateinit var presenter: NavigationPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navContainer.addSystemTopAndBottomPadding()
        icBack.setOnClickListener { onBackPressed() }
        updateButtonState()
        btnDone.setOnClickListener {
            presenter.onDone(vMarkerStart.marker()!!, vMarkerEnd.marker()!!)
        }

        initMarkerInfo()
    }

    private fun initMarkerInfo() {
        with(vMarkerStart) {
            setTitle(getString(R.string.nav_a_title))
            setDescription(getString(R.string.nav_a_desk))
            setSymbol("A")
            setColor("#F2514B", getMapColorData(
                back = createColor(248, 248, 248)
            ))
            onClick {
                presenter.onSelectStart()
                showPointTypeDialog()
            }
        }
        with(vMarkerEnd) {
            setTitle(getString(R.string.nav_b_title))
            setDescription(getString(R.string.nav_b_desk))
            setSymbol("Б")
            setColor("#636363", getMapColorData(
                back = createColor(248, 248, 248)
            ))
            onClick {
                presenter.onSelectEnd()
                showPointTypeDialog()
            }
        }
    }

    private fun showPointTypeDialog() {
        val bottom = PointTypeBSFragment()
        bottom.show(childFragmentManager, "pt")
    }

    override fun onBackPressed() {
        presenter.back()
    }

    override fun addStartMarker(marker: RMMarker) {
        vMarkerStart.addMarker(marker)
        updateButtonState()
    }

    override fun addEndMarker(marker: RMMarker) {
        vMarkerEnd.addMarker(marker)
        updateButtonState()
    }

    private fun updateButtonState() {
        btnDone.isEnabled = vMarkerEnd.marker() != null && vMarkerStart.marker() != null
    }
}