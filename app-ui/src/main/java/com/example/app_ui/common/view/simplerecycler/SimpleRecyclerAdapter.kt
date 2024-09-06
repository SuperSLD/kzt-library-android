package com.example.app_ui.common.view.simplerecycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class SimpleRecyclerAdapter<Model>(
    @LayoutRes private val layoutResId: Int,
    private val createViewHolder: (View) -> SimpleViewHolder<Model>,
    protected var onClickCallback: ((Model, pos: Int) -> Unit)? = null,
) : RecyclerView.Adapter<SimpleViewHolder<Model>>() {

    protected val items: MutableList<Model> = mutableListOf()

    open fun addItem(item: Model) {
        val index = items.size
        items.add(item)
        notifyItemInserted(index)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun swapItems(newItems: List<Model>) {
        if (newItems == items) return
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun updateItemChanged(item: Model) {
        val itemIndex = items.indexOfOrNull(item) ?: return
        notifyItemChanged(itemIndex, Any())
    }

    fun updateItemDeleted(item: Model) {
        val itemIndex = items.indexOfOrNull(item) ?: return
        notifyItemRemoved(itemIndex)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder<Model> {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutResId, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder<Model>, position: Int) {
        val item = items[position]
        holder.bindTo(item, position, onClickCallback)
    }
}

fun <T> List<T>.indexOfOrNull(element: T): Int? {
    val index = indexOf(element)
    return if (index == -1) null else index
}