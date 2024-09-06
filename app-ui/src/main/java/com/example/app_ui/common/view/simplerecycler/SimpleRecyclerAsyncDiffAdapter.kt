package com.example.app_ui.common.view.simplerecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

open class SimpleRecyclerAsyncDiffAdapter<Model>(
    diffCallback: DiffUtil.ItemCallback<Model>,
    @LayoutRes private val layoutResId: Int,
    private val createViewHolder: (View) -> SimpleViewHolder<Model>,
    protected var onClickCallback: ((Model, pos: Int) -> Unit)? = null,
) : ListAdapter<Model, SimpleViewHolder<Model>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder<Model> {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutResId, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder<Model>, position: Int) {
        val item = currentList[position]
        item?.let { holder.bindTo(it, position, onClickCallback) }
    }
}
