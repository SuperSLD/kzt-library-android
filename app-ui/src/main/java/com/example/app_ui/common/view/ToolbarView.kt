package com.example.app_ui.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.app_ui.R
import kotlinx.android.synthetic.main.view_toolbar.view.*
import com.example.app_ui.common.core.base.addSystemTopPadding

class ToolbarView : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defUtils: Int) : super(
        context,
        attributeSet,
        defUtils
    )

    init {
        View.inflate(context, R.layout.view_toolbar, this)
        TVtoolbar.addSystemTopPadding()
    }

    /**
     * Инициализация тулбара.
     */

    fun init(
        title: String = "",
        back: (() -> Unit)? = null,
    ) {
        TVtvTitle.text = title

        if (back != null) {
            TVicClose.visibility = View.VISIBLE
            TVicClose.setOnClickListener { back() }
        }
    }
}