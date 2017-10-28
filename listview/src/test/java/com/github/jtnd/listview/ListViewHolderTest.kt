package com.github.jtnd.listview

import android.view.View
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*

/**
 * Created by nurmjjou on 22/10/2017.
 */
@RunWith(MockitoJUnitRunner::class)
class ListViewHolderTest {
    class Item(val key: String)

    val item = Item("value")
    lateinit var holder: ListViewHolder<Item>

    @Mock lateinit var view: View
    @Mock lateinit var listener: ListViewHolder.OnSelectedListener<Item>

    @Before
    fun setUp() {
        holder = ListViewHolder(view)
    }

    @Test
    fun getItem() {
        holder.item = item

        assertEquals(item, holder.item)
    }

    @Test
    fun onClick_nop() {
        holder.onClick(view)

        verify(listener, times(0)).onSelected(item)
    }

    @Test
    fun onClick_onSelected() {
        holder.item = item
        holder.setOnSelectedListener(listener)

        holder.onClick(view)

        verify(listener).onSelected(item)
    }
}