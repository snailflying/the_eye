package com.theone.eye.ui.user.resetpwd.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
@Parcelize
data class ResetPwdReq(
    @SerializedName("loginname")
    var phoneNumber: String? = null,
    @SerializedName("verifyCode")
    var smsVerifyCode: String? = null,
    var newPwd: String? = null,
    var zoneCode: String? = "+86"
) : Parcelable