package com.example.app_ui.common.core.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.app_ui.R
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * Базовый флоу фрагмент.
 */
abstract class FlowFragment(router: String) : BaseFragment(R.layout.layout_container) {

    private val navigatorHolder: CiceroneHolder by inject()

    private val cicerone = navigatorHolder.getCicerone(router)

    private val currentFragment: BaseFragment?
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    protected val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.container) {
            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)

                fragmentTransaction.setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        cicerone?.getNavigatorHolder()?.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone?.getNavigatorHolder()?.removeNavigator()
        super.onPause()
    }
}