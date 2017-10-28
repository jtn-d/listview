package com.github.jtnd.listview

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by nurmjjou on 21/10/2017.
 */
class ListViewHolder<T>(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private var listener: OnSelectedListener<T>? = null
    var item: T? = null

    interface OnSelectedListener<T> {
        fun onSelected(item: T)
    }

    fun setOnSelectedListener(l: OnSelectedListener<T>) {
        listener = l
    }

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        listener?.onSelected(item!!)
    }
}