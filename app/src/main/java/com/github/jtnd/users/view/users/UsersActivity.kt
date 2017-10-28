package com.github.jtnd.users.view.users

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.jtnd.users.R
import com.github.jtnd.users.databinding.ActivityUsersBinding
import com.github.jtnd.users.viewmodel.UsersViewModel

class UsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_users)

        val viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        binding.vm = viewModel
    }
}
