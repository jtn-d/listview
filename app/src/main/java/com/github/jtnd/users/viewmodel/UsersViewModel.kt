package com.github.jtnd.users.viewmodel

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.example.users.service.users.model.User
import com.example.users.service.users.model.UsersResponse
import com.github.jtnd.listview.ListView
import com.github.jtnd.users.service.users.RandomUserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by nurmjjou on 17/10/2017.
 */
class UsersViewModel : ViewModel(), LifecycleObserver, ListView.OnSelectedListener,
        ListView.OnLoadMoreListener {
    companion object {
        val PAGE_SIZE = 20
    }

    var items: ObservableArrayList<User> = ObservableArrayList<User>()
    var error: ObservableField<String> = ObservableField()
    var loading: ObservableBoolean = ObservableBoolean(false)
    var endOfList: ObservableBoolean = ObservableBoolean(false)

    val service: RandomUserService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        service = retrofit.create(RandomUserService::class.java)
        getUsers(0, PAGE_SIZE)
    }

    fun loadMore(count: Int) {
        getUsers(count/ PAGE_SIZE + 1, PAGE_SIZE)
    }

    fun tryAgain(view: View?) {
        getUsers(1, PAGE_SIZE)
    }

    override fun onLoadMore(page: Int, itemCount: Int) {
        loadMore(itemCount)
    }

    override fun onSelected(index: Int) {
        Log.d("selected", items[index].name.full)
    }

    fun getUsers(page: Int, results: Int) {
        Log.d("users", "${page}/${results}")
        loading.set(true)

        val call = service.fetch(page, results)

        call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>?, response: Response<UsersResponse>?) {
                loading.set(false)
                if (response!!.isSuccessful) {
                    val users = response.body()?.results
                    items.addAll(users!!)
                    if (users.size < PAGE_SIZE) {
                        endOfList.set(true)
                    }
                } else  {
                    error.set("Error")
                }
            }
            override fun onFailure(call: Call<UsersResponse>?, t: Throwable?) {
                loading.set(false)
                error.set(t?.message)
            }
        })
    }
}