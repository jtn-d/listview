package com.example.users.service.users.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by nurmjjou on 17/10/2017.
 */

@Parcelize
class User (
        val email: String,
        val name: Name,
        val picture: Picture,
        val phone: String
) : Parcelable