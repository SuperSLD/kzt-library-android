package com.example.app_ui.common.core.base

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.widget.EditText
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.app_ui.R

/**
 * Базовый клас Activity в single activity приложении.
 */
abstract class ActivityBase: AppCompatActivity() {
    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private var mCloseKeyBoardOnTouch = true

    /**
     * Выбор цвета нижей Ннавигации и стаьус баоа.
     * Метод обязательно должен быть переопределен.
     *
     * @return Pair с statusBarColor и navigationBarColor.
     */
    protected abstract fun getStatusAndNavigationColor(): Pair<Int, Int>

    /**
     * Определяем текущую тему.
     * Дневая или ночная.
     *
     * @return true если тема дневная.
     */
    protected abstract fun themeIsDay(): Boolean

    /**
     * Выбор стартового глобального фрагмента.
     * @return тип глобального фрагмента.
     */
    protected abstract fun getStartFragment(): Fragment

    @CallSuper
    open override fun onCreate(savedInstanceState: Bundle?) {
        initTransparent()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, getStartFragment())
                .commitNow()
        }
    }

    /**
     * Игнорирование закрытия клавиатуры
     * при нажатии не на текстовое поле.
     */
    fun closeKeyBoardOnTouch(close: Boolean) {
        mCloseKeyBoardOnTouch = close
    }

    /**
     * Передача нажатия на кнопку
     * в текущий фрагмент.
     */
    @CallSuper
    open override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private fun initTransparent() {
        window.apply {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

            if (themeIsDay()) {
                decorView.systemUiVisibility = decorView.systemUiVisibility or
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = decorView.systemUiVisibility
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarColor = ContextCompat.getColor(context, getStatusAndNavigationColor().first)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                navigationBarColor = ContextCompat.getColor(context, getStatusAndNavigationColor().second)
            }
        }
    }

    @CallSuper
    open override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && mCloseKeyBoardOnTouch) {
            val v = currentFocus
            if (v is EditText || v is WebView) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}