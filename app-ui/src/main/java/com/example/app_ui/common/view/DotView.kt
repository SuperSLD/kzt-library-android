package com.example.app_ui.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.example.app_ui.R
import kotlinx.android.synthetic.main.view_dot.view.*


class DotView  : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defUtils: Int) : super(
        context,
        attributeSet,
        defUtils
    )

    var colorDisable = 0
    var colorEnable = 0
    var colorError = 0
    var borderDisable = 0

    init {
        View.inflate(context, R.layout.view_dot, this)
    }

    /**
     * Инициализация точеки для пинкода.
     */
    fun init(
        colorEnable: Int,
        colorDisable: Int,
        borderDisable: Int,
        colorError: Int
    ) : DotView {
        this.colorDisable = colorDisable
        this.colorEnable = colorEnable
        this.colorError = colorError
        this.borderDisable = borderDisable

        disable()
        return this
    }

    /**
     * Окрашивание точки в состояние ошибки
     */
    fun error() {
        dot.setCardBackgroundColor(ContextCompat.getColor(context, colorError))
        dot.strokeColor = ContextCompat.getColor(context, colorError)
    }

    /**
     * Окрашивание точки в состояние изначальное
     */
    fun disable() {
        dot.setCardBackgroundColor(ContextCompat.getColor(context, colorDisable))
        dot.strokeColor = ContextCompat.getColor(context, borderDisable)
    }

    /**
     * Окрашивание точки в состояние активное
     */
    fun enable() {
        dot.setCardBackgroundColor(ContextCompat.getColor(context, colorEnable))
        dot.strokeColor = ContextCompat.getColor(context, colorEnable)
    }
}