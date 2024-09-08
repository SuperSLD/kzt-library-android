package com.example.app_ui.screens.main

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.example.app_ui.R
import com.example.app_ui.screens.main.central.FlowCentralScreen
import com.example.app_ui.screens.main.sections.FlowChallengesScreen
import com.example.app_ui.screens.main.documents.FlowDocumentsScreen
import com.example.app_ui.screens.main.education.FlowEducationScreen
import com.example.app_ui.screens.main.profile.FlowProfileScreen
import kotlinx.android.synthetic.main.fragment_main_container.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemBottomPadding
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainContainerFragment : BaseFragment(R.layout.fragment_main_container), MainContainerView {

    @InjectPresenter
    lateinit var presenter: MainContainerPresenter

    private val currentTabFragment: BaseFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? BaseFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigation.addSystemBottomPadding()

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR),
            101
        )
    }

    override fun initBottomNavigation() {
        childFragmentManager.fragments.clear()
        AHBottomNavigationAdapter(activity, R.menu.menu_bottom_navigation).apply {
            setupWithBottomNavigation(bottomNavigation)
        }

        with(bottomNavigation) {
            accentColor =
                    androidx.core.content.ContextCompat.getColor(context, R.color.colorPrimarySecondary)
            inactiveColor =
                    androidx.core.content.ContextCompat.getColor(context, R.color.colorTextSecondary)
            titleState =
                    com.aurelhubert.ahbottomnavigation.AHBottomNavigation.TitleState.ALWAYS_SHOW

            setOnTabSelectedListener { position, wasSelected ->
                if (!wasSelected) {
                    arguments?.putInt(ARG_POSITION, position)
                    selectTab(
                            when (position) {
                                0 -> documentsTab
                                1 -> challengesTab
                                2 -> mainTab
                                3 -> educationTab
                                4 -> profileTab
                                else -> mainTab
                            }
                    )
                }
                true
            }

            selectTab(
                    when (arguments?.getInt(ARG_POSITION)!!) {
                        0 -> documentsTab
                        1 -> challengesTab
                        2 -> mainTab
                        3 -> educationTab
                        4 -> profileTab
                        else -> mainTab
                    }
            )

            bottomNavigation.currentItem = arguments?.getInt(ARG_POSITION)!!
        }
    }

    private fun selectTab(tab: SupportAppScreen) {
        val currentFragment = currentTabFragment
        val newFragment = childFragmentManager.findFragmentByTag(tab.screenKey)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        childFragmentManager.beginTransaction().apply {
            if (newFragment == null) {
                createTabFragment(tab)?.let {
                    replace(
                        R.id.vgFragmentContainer,
                        it,
                        tab.screenKey
                    )
                }
            }
        }.commitNow()
    }

    private fun createTabFragment(tab: SupportAppScreen) = tab.fragment

    override fun changeBottomTab(screen: SupportAppScreen) {
        bottomNavigation.currentItem = when (screen) {
            documentsTab -> 0
            challengesTab -> 1
            mainTab -> 2
            educationTab -> 3
            profileTab -> 4
            else -> START_TAB
        }
        arguments?.putInt(ARG_POSITION, bottomNavigation.currentItem)
        bottomNavigation.currentItem
        selectTab(screen)
    }

    override fun changeBottomNavigationVisibility(isShow: Boolean) {
        bottomNavigation.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun onBackPressed() {
        currentTabFragment?.onBackPressed()
    }

    companion object {
        private val documentsTab = FlowDocumentsScreen()
        private val challengesTab = FlowChallengesScreen()
        private val mainTab = FlowCentralScreen()
        private val educationTab = FlowEducationScreen()
        private val profileTab = FlowProfileScreen()

        private const val ARG_POSITION = "arg_position"

        fun create(): MainContainerFragment {
            val fragment = MainContainerFragment()
            val arg = Bundle()
            arg.putInt(ARG_POSITION, START_TAB)
            fragment.arguments = arg

            return fragment
        }
    }

}

const val START_TAB = 2