package com.example.app_ui.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.app_ui.R
import kotlinx.android.synthetic.main.view_pin_dots.view.dots
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class PinDotsView  : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defUtils: Int) : super(
        context,
        attributeSet,
        defUtils
    )

    private var size = 0
    private val list = mutableListOf<DotView>()
    private var value = ""
    private var correctValue = ""

    private var error = {}
    private var update = {_:String->}
    private var success = {_:String->}

    init {
        View.inflate(context, R.layout.view_pin_dots, this)
    }

    /**
     * Инициализация точек для пинкода.
     * @param size количество точек.
     * @param correctValue правильное значение.
     */
    fun init(
        size: Int,
        colorEnable: Int,
        colorDisable: Int,
        borderDisable: Int,
        colorError: Int,
        correctValue: String = ""
    ) {
        this.size = size
        this.correctValue = correctValue
        for(i in 0 until size) {
            val dot = DotView(context).init(
                colorEnable = colorEnable,
                colorDisable = colorDisable,
                colorError = colorError,
                borderDisable = borderDisable
            )
            list.add(dot)
            dots.addView(dot)
        }
    }

    fun setCorrectPin(pin: String) {
        this.correctValue = pin
    }

    /**
    * Добавление символа к текущему значению
     */
    fun addSymbol(symbol: String) {
        if (symbol.length > 1) throw IllegalArgumentException("string len max 1")
        if (value.length < size) {
            this.value += symbol
            updateDots()
        }
    }

    /**
     * Отчистка поля.
     */
    fun clear() {
        value = ""
        updateDots()
    }

    /**
     * Удаление последнего символа строки.
     */
    fun deleteLast() {
        this.value = this.value.dropLast(1)
        updateDots()
    }

    /**
     * Слушаем правильное заполнение
     */
    fun onSuccess(success: (String)->Unit) {
        this.success = success
    }

    /**
     * Слушаем обновление
     */
    fun onUpdate(update: (String)->Unit) {
        this.update = update
    }

    /**
     * Слушаем заполнение с ошибкой
     */
    fun onError(error: ()->Unit) {
        this.error = error
    }

    /**
     * Обновление состояния точек.
     */
    private fun updateDots() {
        update(value)
        if (
            correctValue.isNotEmpty() &&
                    value.length == size && value != correctValue
        ) {
            for (dot in list) {
                dot.error()
            }
            error()
            GlobalScope.launch {
                delay(200)
                value = ""
                withContext(Dispatchers.Main) {
                    updateDots()
                }
            }.start()
        } else {
            for (i in 0 until size) {
                if (value.length > i) {
                    list[i].enable()
                } else {
                    list[i].disable()
                }
            }
            if (value.length == size) success(value)
        }
    }
}