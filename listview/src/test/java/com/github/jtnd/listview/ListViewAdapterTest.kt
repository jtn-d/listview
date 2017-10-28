package com.github.jtnd.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by nurmjjou on 22/10/2017.
 */
@RunWith(MockitoJUnitRunner::class)
class ListViewAdapterTest {
    class Item(val key: String)

    val item = Item("value")

    lateinit var adapter: ListViewAdapter<Item>

    @Mock lateinit var holder: ListViewHolder<Item>
    @Mock lateinit var parent: ViewGroup
    @Mock lateinit var view: View
    @Mock lateinit var layoutInflater: LayoutInflater
    @Mock lateinit var selectedListener: ListViewAdapter.OnSelectedListener<Item>
    @Mock lateinit var bindListener: ListViewAdapter.OnBindListener<Item>

    @Before
    fun setUp() {
        `when`(layoutInflater.inflate(0, parent, false)).thenReturn(view)
        `when`(layoutInflater.inflate(1, parent, false)).thenReturn(view)

        adapter = ListViewAdapter(layoutInflater, 0, 1)
    }

    @Test
    fun setItems() {
        adapter.setItems(listOf(item))
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun getItemCount_shouldReturnZero() {
        assertEquals(0, adapter.itemCount)
    }

    @Test
    fun getItemCount_shouldReturnTwo() {
        adapter.setItems(listOf(item))
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun getItemViewType_shouldReturnOne() {
        // TODO error
        assertEquals(1, adapter.getItemViewType(0))
    }

    @Test
    fun getItemViewType_shouldReturnZero() {
        adapter.setItems(listOf(item))

        assertEquals(0, adapter.getItemViewType(0))
        assertEquals(1, adapter.getItemViewType(1))
    }

    @Test
    fun onCreateViewHolder_createTypeZero() {
        var holder = adapter.onCreateViewHolder(parent, 0)

        assertNotNull(holder)
    }

    @Test
    fun onCreateViewHolder_createTypeOne() {
        var holder = adapter.onCreateViewHolder(parent, 1)

        assertNotNull(holder)
    }

    @Test
    fun onBindViewHolder_nop() {
        adapter.onBindViewHolder(holder, 0)

        assertEquals(null, holder.item)
    }

    @Test
    fun onBindViewHolder_shouldBindUser() {
        adapter.setOnBindListener(bindListener)
        adapter.setItems(listOf(item))

        adapter.onBindViewHolder(holder, 0)

        //assertEquals(item, holder.item)
        verify(bindListener).onBindItem(holder, 0)
    }

    @Test
    fun onSelected() {
        adapter.setOnSelectedListener(selectedListener)
        adapter.setItems(listOf(item))

        adapter.onSelected(item)

        verify(selectedListener).onSelected(item, 0)
    }

}