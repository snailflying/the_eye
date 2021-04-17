package com.theone.eye.ui.user.resetpwd.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ResetPwdRes(
    var result: Boolean = false,
    var errorMsg: String? = null
) : Parcelable