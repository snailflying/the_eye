package com.theone.eye.ui.user.register.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RegisterRes(
    var token: String? = null,
    var refreshToken: String? = null,
    var userId: String? = null,
    var name: String? = null,
    var cellphone: String? = null,
    var email: String? = null,
    var errorMsg: String? = null
) : Parcelable