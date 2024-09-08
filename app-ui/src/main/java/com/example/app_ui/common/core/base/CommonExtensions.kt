package com.example.app_ui.common.core.base

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun String.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
    cal.time = sdf.parse(this)
    return cal
}


fun Calendar.toDateString(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
    return sdf.format(this.time)
}