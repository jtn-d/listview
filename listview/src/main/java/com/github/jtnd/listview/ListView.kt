package com.github.jtnd.listview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater


/**
 * Created by nurmjjou on 22/10/2017.
 */
abstract class ListView<T> : RecyclerView, ListViewAdapter.OnBindListener<T>,
        ListViewAdapter.OnSelectedListener<T> {
    private var scrollListener: EndlessRecyclerViewScrollListener
    private var endOfList: Boolean = false
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ListView, 0, 0)
        try {
            val listItem = a.getResourceId(R.styleable.ListView_listitem, 0)
            val moreItem = a.getResourceId(R.styleable.ListView_moreitem, 0)
            adapter = ListViewAdapter<T>(LayoutInflater.from(context), listItem, moreItem)
        } finally {
            a.recycle()
        }

        layoutManager = LinearLayoutManager(context)
        itemsAdapter.setOnBindListener(this)
        itemsAdapter.setOnSelectedListener(this)

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (!endOfList) {
                    loadMoreListener?.onLoadMore(page, totalItemsCount)
                }
            }
        }

        addOnScrollListener(scrollListener)
    }

    val itemsAdapter: ListViewAdapter<T>
        get() = adapter as ListViewAdapter<T>

    fun setItems(items: List<T>) {
        itemsAdapter.setItems(items)
        itemsAdapter.notifyDataSetChanged()
    }

    fun setEndOfList(eol: Boolean) {
        endOfList = eol
        itemsAdapter.setEndOfList(eol)
    }

    abstract override fun onBindItem(holder: ListViewHolder<T>?, position: Int)

    interface OnSelectedListener {
        fun onSelected(index: Int)
    }

    private var selectedListener: OnSelectedListener? = null

    fun setOnSelectedListener(l: OnSelectedListener) {
        selectedListener = l
    }

    override fun onSelected(item: T, index: Int) {
        selectedListener?.onSelected(index)
    }

    interface OnLoadMoreListener {
        fun onLoadMore(page: Int, itemCount: Int)
    }

    private var loadMoreListener: OnLoadMoreListener? = null

    fun setOnLoadMoreListener(l: OnLoadMoreListener) {
        loadMoreListener = l
    }
}