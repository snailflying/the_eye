package com.theone.eye.ui.user.login.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LoginReq(
    var phoneNumber: String? = null,
    var pwd: String? = null
) : Parcelable