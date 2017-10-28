package com.github.jtnd.users.service.users

import com.example.users.service.users.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by nurmjjou on 15/10/2017.
 */
interface RandomUserService {
    @GET("/api")
    fun fetch(@Query("page") page:Int, @Query("results") results:Int)
        : Call<UsersResponse>
}