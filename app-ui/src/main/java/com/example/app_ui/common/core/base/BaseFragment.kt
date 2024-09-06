package com.example.app_ui.common.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Базовый класс фрагмента.
 */
abstract class BaseFragment(private val layoutRes: Int) : MvpAppCompatFragment() {

    private var instanceStateSaved: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
    }

    abstract fun onBackPressed()
}