package com.example.app_ui.screens.main.central.roadmap.navigation

import android.graphics.Color
import com.arellomobile.mvp.InjectViewState
import com.example.app_domain.controllers.*
import com.example.app_ui.R
import com.example.app_ui.screens.main.central.roadmap.selectpoint.SelectPointScreen
import com.example.app_ui.screens.main.central.roadmap.selectroom.SelectRoomScreen
import online.jutter.roadmapview.data.models.map.RMMarker
import online.jutter.supersld.common.base.BasePresenter
import org.koin.core.inject

@InjectViewState
class NavigationPresenter : BasePresenter<NavigationView>() {

    private val selectMarkerController: SelectMarkerController by inject()
    private val navigationController: NavigationController by inject()
    private val selectRoomController: SelectRoomController by inject()
    private val pointTypeController: PointTypeController by inject()
    private val bottomVisibilityController: BottomVisibilityController by inject()
    private var isStartMarker = false
    private var selectRoom = false

    override fun attachView(view: NavigationView?) {
        super.attachView(view)
        bottomVisibilityController.setVisible(false)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        listenMarker()
        listenPointType()
        listenRoom()
        val navData = navigationController.get()
        if (navData != null) {
            viewState.addStartMarker(navData.first)
            viewState.addEndMarker(navData.second)
        }
    }

    fun onSelectStart() {
        isStartMarker = true
    }

    fun onSelectEnd() {
        isStartMarker = false
    }

    fun onDone(start: RMMarker, end: RMMarker) {
        navigationController.setMarkers(start, end)
        back()
    }

    private fun listenPointType() {
        pointTypeController.state
            .listen {
                selectRoom = it == PointType.ROOM
                if (it  == PointType.MAP) {
                    if (isStartMarker) {
                        router?.navigateTo(
                            SelectPointScreen(
                                "start",
                                R.string.nav_a_title,
                                R.string.nav_a_desk,
                                "А",
                                "#F2514B",
                            )
                        )
                    } else {
                        router?.navigateTo(
                            SelectPointScreen(
                                "end",
                                R.string.nav_b_title,
                                R.string.nav_b_desk,
                                "Б",
                                "#636363",
                            )
                        )
                    }
                } else {
                    router?.navigateTo(SelectRoomScreen())
                }
            }.connect()
    }

    private fun listenMarker() {
        selectMarkerController.state
            .listen {
                it.name = "Точка " + if (isStartMarker) "А" else "Б"
                navigationController.clearData()
                if (isStartMarker) {
                    viewState.addStartMarker(it)
                } else {
                    viewState.addEndMarker(it)
                }
            }.connect()
    }

    private fun listenRoom() {
            selectRoomController.state.listen {
                if (isStartMarker) {
                    viewState.addStartMarker(
                        RMMarker(
                            id = "start",
                            symbol = "A",
                            x = it.position.x.toFloat(),
                            y = it.position.y.toFloat(),
                            floor = it.position.floor,
                            color = Color.parseColor("#F2514B"),
                            textColor = Color.parseColor("#FFFFFF"),
                            name = it.name,
                        )
                    )
                } else {
                    viewState.addEndMarker(
                        RMMarker(
                            id = "end",
                            symbol = "Б",
                            x = it.position.x.toFloat(),
                            y = it.position.y.toFloat(),
                            floor = it.position.floor,
                            color = Color.parseColor("#636363"),
                            textColor = Color.parseColor("#FFFFFF"),
                            name = it.name,
                        )
                    )
                }
            }
    }

    fun back() {
        router?.exit()
    }
}