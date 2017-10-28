package com.example.users.service.users.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by nurmjjou on 17/10/2017.
 */
@Parcelize
class Name (
        var title: String,
        var first: String,
        var last: String
) : Parcelable {
    init {
        title = title.substring(0, 1).capitalize() + title.substring(1)
        first = first.substring(0, 1).capitalize() + first.substring(1)
        last = last.substring(0, 1).capitalize() + last.substring(1)
    }
    val full: String
        get() {
            title = title.substring(0, 1).capitalize() + title.substring(1)
            first = first.substring(0, 1).capitalize() + first.substring(1)
            last = last.substring(0, 1).capitalize() + last.substring(1)
            return "${title} ${first} ${last}"
        }
}