package com.github.jtnd.users.view.users

import android.content.Context
import android.util.AttributeSet
import com.example.users.service.users.model.User
import com.github.jtnd.listview.ListView
import com.github.jtnd.listview.ListViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_listitem.view.*


/**
 * Created by nurmjjou on 21/10/2017.
 */
class UserListView : ListView<User> {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onBindItem(holder: ListViewHolder<User>?, position: Int) {
        val view = holder?.itemView!!
        val item = holder.item!!

        view.full_name?.text = item.name.full
        view.email?.text = item.email
        Picasso.with(context)
                .load(item.picture.thumbnail)
                .into(view.photo)
    }
}