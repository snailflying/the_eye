package com.theone.eye.ui.user.login.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class LoginRes(
    var name: String? = null,
    var sex: Int? = null,
    @SerializedName("imgpath")
    var imgPath: String? = null
) : Parcelable