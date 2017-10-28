package com.example.users.service.users.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by nurmjjou on 17/10/2017.
 */
@Parcelize
class Picture (
        val large: String,
        val medium: String,
        val thumbnail: String
) : Parcelable