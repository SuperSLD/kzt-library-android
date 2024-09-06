package com.example.app_ui.screens.main.education.course.lesson

import android.os.Parcelable
import android.view.View
import com.bumptech.glide.Glide
import com.example.app_ui.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.lineholder_image.view.*
import online.juter.supersld.view.input.form.formadapter.JTFormBaseHolder
import online.juter.supersld.view.input.form.formadapter.bindLayout
import online.juter.supersld.view.input.form.lines.JTFormLine

@Parcelize
open class ImageLine(
    val image: String,
): JTFormLine, Parcelable {

    override fun getAdapterData(): Pair<Int, Class<*>> =
        R.layout.lineholder_image bindLayout ImageLineHolder::class.java

    override fun valid() = true
    override fun editable() = false
    override fun getLineId(): String?  = null
    override fun getValueType(): Class<*>? = null
    override fun getLineValue(): Any? = null
}

class ImageLineHolder(itemView: View) : JTFormBaseHolder(itemView) {
    override fun bind(line: JTFormLine) {
        line as ImageLine
        with(itemView) {
            Glide.with(context).load(line.image).into(lineholder_image)
        }
    }
}