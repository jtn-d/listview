package com.github.jtnd.listview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by nurmjjou on 21/10/2017.
 */
class ListViewAdapter<T>(layoutInflater: LayoutInflater, itemLayout: Int, moreLayout: Int)
    : RecyclerView.Adapter<ListViewHolder<T>>(), ListViewHolder.OnSelectedListener<T> {
    private var layoutInflater: LayoutInflater = layoutInflater
    private var itemLayout: Int = itemLayout
    private var moreLayout: Int = moreLayout

    private var items: List<T> = ArrayList()
    private var endOfList: Boolean = false

    override fun getItemCount(): Int {
        if (!endOfList) {
            return if (items.size > 0) items.size + 1 else 0
        } else {
            return items.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (!endOfList) {
            return if (position < items.size) 0 else 1
        } else {
            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder<T> {
        var holder: ListViewHolder<T>
        if (viewType == 0) {
            val view = layoutInflater.inflate(itemLayout, parent, false)
            holder = ListViewHolder(view)
        } else {
            val view = layoutInflater.inflate(moreLayout, parent, false)
            holder = ListViewHolder(view)
        }
        holder.setOnSelectedListener(this)
        return holder
    }

    interface OnBindListener<T> {
        fun onBindItem(holder: ListViewHolder<T>?, position: Int)
    }

    private var bindListener: OnBindListener<T>? = null

    fun setOnBindListener(l: OnBindListener<T>) {
        bindListener = l
    }

    override fun onBindViewHolder(holder: ListViewHolder<T>?, position: Int) {
        if (position < items.size) {
            holder?.item = items[position]
            bindListener?.onBindItem(holder, position)
        }
    }

    fun setItems(results: List<T>?) {
        items = results!!
    }

    interface OnSelectedListener<T> {
        fun onSelected(item: T, index: Int)
    }

    private var selectedListener: OnSelectedListener<T>? = null

    fun setOnSelectedListener(l: OnSelectedListener<T>) {
        selectedListener = l
    }

    override fun onSelected(item: T) {
        selectedListener?.onSelected(item, items.indexOf(item))
    }

    fun setEndOfList(eol: Boolean) {
        this.endOfList = eol
    }
}
