package com.example.app_ui.screens.main.central.roadmap.selectroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ui.R
import kotlinx.android.synthetic.main.viewholder_room.view.*
import online.jutter.roadmapview.data.models.map.RMRoom

class RoomsAdapter(
    val roomClick: (RMRoom)->Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = listOf<RMRoom>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.viewholder_room, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setSearchFilter(search: String) {
        this.list = list.sortedBy { levenshtein(it.name, search) }
        notifyDataSetChanged()
    }

    fun addData(rooms: List<RMRoom>) {
        this.list = rooms
        notifyDataSetChanged()
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(room: RMRoom) {
            with(itemView) {
                tvTitle.text = room.name
                val addressName = room.structName + (if (room.structName!!.isNotEmpty()) ", " else "") + room.address
                if (addressName.length < 3) {
                    tvNameAddress.visibility = View.GONE
                } else {
                    tvNameAddress.text = addressName
                    tvNameAddress.visibility = View.VISIBLE
                }
                mainCard.setOnClickListener {
                    roomClick(room)
                }
            }
        }
    }
}

fun levenshtein(s: String, t: String): Int {
    // degenerate cases
    if (s == t)  return 0
    if (s == "") return t.length
    if (t == "") return s.length

    // create two integer arrays of distances and initialize the first one
    val v0 = IntArray(t.length + 1) { it }  // previous
    val v1 = IntArray(t.length + 1)         // current

    var cost: Int
    for (i in 0 until s.length) {
        // calculate v1 from v0
        v1[0] = i + 1
        for (j in 0 until t.length) {
            cost = if (s[i] == t[j]) 0 else 1
            v1[j + 1] = Math.min(v1[j] + 1, Math.min(v0[j + 1] + 1, v0[j] + cost))
        }
        // copy v1 to v0 for next iteration
        for (j in 0 .. t.length) v0[j] = v1[j]
    }
    return v1[t.length]
}
