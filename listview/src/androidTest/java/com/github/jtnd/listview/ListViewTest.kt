package com.github.jtnd.listview

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.AttributeSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

/**
 * Created by nurmjjou on 22/10/2017.
 */
@RunWith(AndroidJUnit4::class)
class ListViewTest {
    class Item(val key: String)

    class ItemListView : ListView<Item> {
        constructor(context: Context) : this(context, null)
        constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
        constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
        override fun onBindItem(holder: ListViewHolder<Item>?, position: Int) {}
    }

    val item = Item("value")

    lateinit var listView: ItemListView

    @Rule @JvmField val rule = MockitoJUnit.rule()

    @Mock lateinit var selectedListener: ListView.OnSelectedListener
    @Mock lateinit var loadMoreListener: ListView.OnLoadMoreListener

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        listView = ItemListView(context)
    }

    @Test
    fun constructor () {
        assertNotNull(listView.itemsAdapter)
        assertNotNull(listView.layoutManager)
    }

    @Test
    fun setItems() {
        listView.setItems(listOf(item))

        assertEquals(2, listView.itemsAdapter.itemCount)
    }

    @Test
    fun onSelected() {
        listView.setOnSelectedListener(selectedListener)

        val index = 3
        listView.onSelected(item, index)

        verify(selectedListener).onSelected(index)
    }
}