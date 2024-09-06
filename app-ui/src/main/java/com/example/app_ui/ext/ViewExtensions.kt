package com.example.app_ui.ext

import android.app.ActionBar
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun View.getColor(id: Int) = ContextCompat.getColor(context, id)
fun View.getDrawable(id: Int) = ContextCompat.getDrawable(context, id)
fun View.getString(id: Int) = context.getString(id)

fun Fragment.getColor(id: Int) = ContextCompat.getColor(requireContext(), id)
fun Fragment.getDrawable(id: Int) = ContextCompat.getDrawable(requireContext(), id)

/**
 * Скрытие View с анимацией сжатия.
 */
fun View.expand(dps: Float = 1F, setWrapContent: Boolean = true) {
    val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec((this.parent as View).width, View.MeasureSpec.EXACTLY)
    val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    this.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
    val targetHeight = this.measuredHeight

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    this.layoutParams.height = 1
    this.visibility = View.VISIBLE
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            this@expand.layoutParams.height =
                if (interpolatedTime == 1f) {
                    if (setWrapContent) {
                        ActionBar.LayoutParams.WRAP_CONTENT
                    } else {
                        (targetHeight * interpolatedTime).toInt()
                    }
                } else {
                    (targetHeight * interpolatedTime).toInt()
                }
            this@expand.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // Expansion speed of 1dp/ms
    a.duration = (targetHeight / this.context.resources.displayMetrics.density / dps).toLong()
    this.startAnimation(a)
}

/**
 * Появление View с анимацией расширения.
 */
fun View.collapse(dps: Float = 1F) {
    val initialHeight = this.measuredHeight
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            if (interpolatedTime == 1f) {
                this@collapse.visibility = View.GONE
            } else {
                this@collapse.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                this@collapse.requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // Collapse speed of 1dp/ms
    a.duration = (initialHeight / this.context.resources.displayMetrics.density / dps).toLong()
    this.startAnimation(a)
}

fun View.setVisible(show: Boolean, vType: Int = View.GONE) {
    this.visibility = if (show) View.VISIBLE else vType
}

fun View.hide() = setVisible(false)
fun View.show() = setVisible(true)

fun EditText.textChanged(listener: (String)->Unit) {
    this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listener(s.toString())
            }
        }
    )
}

fun Vibrator.shortVibration() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrate(100)
    }
}