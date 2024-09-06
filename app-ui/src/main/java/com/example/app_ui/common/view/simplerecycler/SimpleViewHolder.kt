package com.example.app_ui.common.view.simplerecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class SimpleViewHolder<T>(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bindTo(item: T, pos: Int, onClickCallback: ((T, Int) -> Unit)? = null)
}
